package com.adamhaynes.finances;

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
    private static final Dollars BALANCE = new Dollars(10000);
    private static final Dollars PRINCIPAL = new Dollars(3000);
    private static final InterestRate INTEREST_RATE = new InterestRate(10);
    private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

    private StockMarketYear newYear(){
        return new StockMarketYear(BALANCE, PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE);
    }

    @Test
    public void startingValues(){

        StockMarketYear year = newYear();
        assertEquals("balance", BALANCE, year.startingBalance());
        assertEquals("principal", PRINCIPAL, year.startingPrincipal());
        assertEquals("interest", INTEREST_RATE, year.interestRate());
        assertEquals("tax rate", CAPITAL_GAINS_TAX_RATE, year.capitalGainsTaxRate());
        assertEquals("total withdrawn default", new Dollars(0), year.totalWithdrawn());
    }

    @Test
    public void capitalGainsTax(){
        StockMarketYear year = newYear();
        year.withdraw(new Dollars(4000));
        int capitalGainsTax = 250;
        int withdrawalsToCoverCapitalGainsTax = 83;

        assertEquals("capital gains tax includes tax on withdrawals to cover capital gains",
                new Dollars(capitalGainsTax + withdrawalsToCoverCapitalGainsTax),
                year.capitalGainsTaxIncurred());

        assertEquals("total withdrawn includes capital gains tax",
                new Dollars(4000 + capitalGainsTax + withdrawalsToCoverCapitalGainsTax),
                year.totalWithdrawn());
    }

    @Test
    public void interestEarned(){
        StockMarketYear year = newYear();
        assertEquals("basic interest earned", new Dollars(1000), year.appreciation());
        year.withdraw(new Dollars(2000));
        assertEquals("withdrawals don't earn interest", new Dollars(800), year.appreciation());
        year.withdraw(new Dollars(2000));
        assertEquals("capital gains tax withdrawals don't ear interest", new Dollars(566), year.appreciation());
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
        assertEquals("ending balance includes withdrawals", new Dollars(9900), year.endingBalance());
        year.withdraw(new Dollars(3000));
        assertEquals("ending balance includes capital gains withdrawals", new Dollars(6233), year.endingBalance());

    }

    @Test
    public void nextYearStaringValuesMatchesThisYearsEndingValues(){
        StockMarketYear year = newYear();
        assertEquals("balance", year.endingBalance(), year.nextYear().startingBalance());
        assertEquals("principal", year.endingPrincipal(), year.nextYear().startingPrincipal());
        assertEquals("interest", year.interestRate(), year.nextYear().interestRate());
        assertEquals("capital gains tax", year.capitalGainsTaxRate(), year.nextYear().capitalGainsTaxRate());
    }
}
