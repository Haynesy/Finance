package com.adamhaynes.finances.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 *
 * User: Adam.Haynes
 * Date: 31/07/12
 * Time: 12:23 PM
 *
 */
public class StockMarketYearTest {

    // Starting stock market values
    private static final Dollars STARTING_BALANCE = new Dollars(10000);
    private static final Dollars STARTING_PRINCIPAL = new Dollars(3000);
    private static final InterestRate INTEREST_RATE = new InterestRate(10);
    private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);
    private static final Year YEAR = new Year(2010);

    private StockMarketYear newYear(){
        return new StockMarketYear(YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE);
    }

    @Test
    public void startingValues(){

        StockMarketYear year = newYear();
        assertEquals("balance", STARTING_BALANCE, year.startingBalance());
        assertEquals("principal", STARTING_PRINCIPAL, year.startingPrincipal());
        assertEquals("interest", INTEREST_RATE, year.interestRate());
        assertEquals("tax rate", CAPITAL_GAINS_TAX_RATE, year.capitalGainsTaxRate());
        assertEquals("total withdrawn default", new Dollars(0), year.totalWithdrawn());
        assertEquals("year", YEAR, year.year());
    }

    @Test
    public void capitalGainsTax(){
        StockMarketYear year = newYear();
        year.withdraw(new Dollars(4000));
        int capitalGainsTax = 250;
        int withdrawalsToCoverCapitalGainsTax = 83;

        assertEquals("capital gains tax includes tax on withdrawals to cover capital gains",
                new Dollars(1000 + capitalGainsTax + withdrawalsToCoverCapitalGainsTax),
                year.capitalGainsTaxIncurred());

        assertEquals("total withdrawn includes capital gains tax",
                new Dollars(5000 + capitalGainsTax + withdrawalsToCoverCapitalGainsTax),
                year.totalWithdrawn());
    }

    @Test
    public void treatAllWithdrawalsAsSubjectToTaxUntilAllCapitalGainsHasBeenWithdrawn(){
        StockMarketYear year = newYear();
        Dollars capitalGains = STARTING_BALANCE.minus(STARTING_PRINCIPAL);

        year.withdraw(new Dollars(500));
        assertEquals("pay tax on entire withdrawal",
                        new Dollars(167), year.capitalGainsTaxIncurred());

        year.withdraw(capitalGains);
        assertEquals("pay tax on all withdrawals until capital gains is withdrawn",
                new Dollars(2333), year.capitalGainsTaxIncurred());

        year.withdraw(new Dollars(1000));
        assertEquals("pay no more tax once all capital gains withdrawn",
                new Dollars(2333), year.capitalGainsTaxIncurred());
    }

    @Test
    public void interestEarned(){
        StockMarketYear year = newYear();
        assertEquals("basic interest earned", new Dollars(1000), year.appreciation());
        year.withdraw(new Dollars(2000));
        assertEquals("withdrawals (which pay capital gains tax) don't earn interest", new Dollars(733), year.appreciation());
    }

    @Test
    public void endingPrincipal(){

        StockMarketYear year = newYear();
        year.withdraw(new Dollars(1000));
        assertEquals("ending principal considers withdrawals", new Dollars(2000), year.endingPrincipal());
        year.withdraw(new Dollars(500));
        assertEquals("ending principal totals multiple withdrawals", new Dollars(1500), year.endingPrincipal());
        year.withdraw(new Dollars(3000));
        assertEquals("ending principal never goes below zero", new Dollars(0), year.endingPrincipal());
    }

    @Test
    public void endingBalance(){
        StockMarketYear year = newYear();
        assertEquals("ending balance includes interest", new Dollars(11000), year.endingBalance());
        year.withdraw(new Dollars(1000));
        assertEquals("ending balance includes withdrawals (which pay capital gains tax) and interest",
                new Dollars(9533), year.endingBalance());
    }

    @Test
    public void stockMarketYearKnowsWhichYearItRepresents(){
        StockMarketYear market = newYear();
        assertEquals(new Year(2010), market.year());
    }

    @Test
    public void nextYearStaringValuesMatchesThisYearsEndingValues(){
        StockMarketYear year = newYear();
        assertEquals("balance", year.endingBalance(), year.nextYear().startingBalance());
        assertEquals("principal", year.endingPrincipal(), year.nextYear().startingPrincipal());
        assertEquals("interest", year.interestRate(), year.nextYear().interestRate());
        assertEquals("capital gains tax", year.capitalGainsTaxRate(), year.nextYear().capitalGainsTaxRate());
        assertEquals("year", new Year(2011), year.nextYear().year());
    }
}
