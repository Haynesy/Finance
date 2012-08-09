package com.adamhaynes.finances;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 9/08/12
 * Time: 2:02 PM
 */
public class StockMarketTest {

    private static final int STARTING_YEAR = 2010;
    private static final int ENDING_YEAR = 2050;
    private static final Dollars STARTING_BALANCE = new Dollars(10000);
    private static final Dollars STARTING_PRINCIPAL = new Dollars(7000);
    private static final InterestRate INTEREST_RATE = new InterestRate(10);
    private static final TaxRate CAPITAL_GAINS_TAX = new TaxRate(25);

    @Test
    public void stockMarketContainsMupltipleYears(){
        StockMarket account = new StockMarket(STARTING_YEAR, ENDING_YEAR, STARTING_BALANCE,
                STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX);
        assertEquals(41, account.numberOfYears());
        assertEquals("first year", STARTING_BALANCE, account.getYear(0).startingBalance());
        assertEquals("second year", new Dollars(11000), account.getYear(1).startingBalance());
        assertEquals("third year", new Dollars(12100), account.getYear(2).startingBalance());
    }
}
