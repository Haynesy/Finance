package com.adamhaynes.finances.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 9/08/12
 * Time: 2:02 PM
 */
public class StockMarketTest {

    private static final Year STARTING_YEAR = new Year(2010);
    private static final Year ENDING_YEAR = new Year(2050);
    private static final Dollars STARTING_BALANCE = new Dollars(10000);
    private static final Dollars STARTING_PRINCIPAL = new Dollars(7000);
    private static final InterestRate INTEREST_RATE = new InterestRate(10);
    private static final TaxRate CAPITAL_GAINS_TAX = new TaxRate(25);

    @Test
    public void stockMarketContainsMultipleYears(){
        StockMarket account = new StockMarket(STARTING_YEAR, ENDING_YEAR, STARTING_BALANCE,
                STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX);
        assertEquals(41, account.numberOfYears());
        assertEquals("first year", STARTING_BALANCE, account.getYearOffset(0).startingBalance());
        assertEquals("second year", new Dollars(11000), account.getYearOffset(1).startingBalance());
        assertEquals("third year", new Dollars(12100), account.getYearOffset(2).startingBalance());
        assertEquals("last year", new Year(2050), account.getYearOffset(40).year());
    }


}
