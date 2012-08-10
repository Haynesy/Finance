package com.adamhaynes.finances.ui;

import com.adamhaynes.finances.domain.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 8/08/12
 * Time: 7:28 PM
 */
public class StockMarketTableModelTest {

    private StockMarketTableModel model;
    private static final Year STARTING_YEAR = new Year(2010);
    private static final Year ENDING_YEAR = new Year(2050);
    private static final Dollars STARTING_BALANCE = new Dollars(10000);
    private static final Dollars STARTING_PRINCIPAL = new Dollars(7000);

    @Before
    public void setup() {
        StockMarket market = new StockMarket(STARTING_YEAR, ENDING_YEAR, STARTING_BALANCE,
                        STARTING_PRINCIPAL, new InterestRate(10), new TaxRate(25));
        model = new StockMarketTableModel(market);
    }

    @Test
    public void columns(){
        assertEquals("total columns", 6, model.getColumnCount());
        assertEquals("Year", model.getColumnName(0));
        assertEquals("Starting Balance", model.getColumnName(1));
        assertEquals("Starting Principal", model.getColumnName(2));
    }

    @Test
    public void oneRow(){
        assertEquals("year", STARTING_YEAR, model.getValueAt(0, 0));
        assertEquals("starting balance", STARTING_BALANCE, model.getValueAt(0, 1));
        assertEquals("starting principal", STARTING_PRINCIPAL, model.getValueAt(0, 2));
        assertEquals("withdrawals", new Dollars(0), model.getValueAt(0, 3));
        assertEquals("appreciation", new Dollars(1000), model.getValueAt(0, 4));
        assertEquals("ending balance", new Dollars(11000), model.getValueAt(0, 5));
    }

    @Test
    public void multipleRows(){
        assertEquals("total rows", 41, model.getRowCount());
        assertEquals("starting year", STARTING_YEAR, model.getValueAt(0, 0));
        assertEquals("ending year", ENDING_YEAR, model.getValueAt(ENDING_YEAR.toInt() - STARTING_YEAR.toInt(), 0));
        assertEquals("starting balance of next year", new Dollars(11000), model.getValueAt(1, 1));
    }
}
