package com.adamhaynes.finances.ui;

import com.adamhaynes.finances.domain.StockMarket;
import com.adamhaynes.finances.domain.StockMarketYear;
import com.adamhaynes.finances.util.UnreachableCodeException;

import javax.swing.table.AbstractTableModel;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 8/08/12
 * Time: 7:23 PM
 */
public class StockMarketTableModel extends AbstractTableModel {

    private static final String[] COLUMN_TITLES = {"Year", "Starting Balance", "Starting Principal",
                    "Withdrawals", "Appreciation", "Ending Balance"};

    private StockMarket market;

    public StockMarketTableModel(StockMarket market){
        this.market = market;
    }

    public int getRowCount() {
        return market.numberOfYears();
    }

    public int getColumnCount() {
        return COLUMN_TITLES.length;
    }

    public String getColumnName(int index){
        return COLUMN_TITLES[index];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        StockMarketYear marketYear = market.getYearOffset(rowIndex);
        switch(columnIndex){
            case 0: return marketYear.year();
            case 1: return marketYear.startingBalance();
            case 2: return marketYear.startingPrincipal();
            case 3: return marketYear.totalWithdrawn();
            case 4: return marketYear.appreciation();
            case 5: return marketYear.endingBalance();
            default: throw new UnreachableCodeException();
        }
    }
}
