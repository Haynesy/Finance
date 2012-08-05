package com.adamhaynes.finances;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Adam.Haynes
 * Date: 31/07/12
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockMarketYearTest {

    // Starting stock market values
    private static final int BALANCE = 10000;
    private static final int PRINCIPAL = 3000;
    private static final int INTEREST_RATE = 10;
    private static final int CAPITAL_GAINS_TAX_RATE = 25;

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
        assertEquals("total withdrawn default", 0, year.totalWithdrawn());
    }

    @Test
    public void capitalGainsTax(){
        StockMarketYear year = newYear();
        year.withdraw(4000);
        int capitalGainsTax = 250;
        int withdrawalsToCoverCapitalGainsTax = 83;
        assertEquals("capital gains tax includes tax on withdrawals to cover capital gains",
                capitalGainsTax + withdrawalsToCoverCapitalGainsTax,
                year.capitalGainsTaxIncurred());
        assertEquals("total withdrawn includes capital gains tax",
                4000 + capitalGainsTax + withdrawalsToCoverCapitalGainsTax,
                year.totalWithdrawn());
    }

    @Test
    public void interestEarned(){
        StockMarketYear year = newYear();
        assertEquals("basic interest earned", 1000, year.interestEarned());
        year.withdraw(2000);
        assertEquals("withdrawals don't earn interest", 800, year.interestEarned());
        year.withdraw(2000);
        assertEquals("capital gains tax withdrawals don't ear interest", 566, year.interestEarned());
    }

    @Test
    public void endingPrincipal(){

        StockMarketYear year = newYear();
        year.withdraw(1000);
        assertEquals("ending principal considers withdrawals", 2000, year.endingPrincipal());
        year.withdraw(500);
        assertEquals("ending principal totals multiple withdrawals", 1500, year.endingPrincipal());
        year.withdraw(3000);
        assertEquals("ending principal never goes below zero", 0, year.endingPrincipal());
    }

    @Test
    public void endingBalance(){
        StockMarketYear year = newYear();
        assertEquals("ending balance includes interest", 11000, year.endingBalance());
        year.withdraw(1000);
        assertEquals("ending balance includes withdrawals", 9900, year.endingBalance());
        year.withdraw(3000);
        assertEquals("ending balance includes capital gains withdrawals", 6233, year.endingBalance());

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
