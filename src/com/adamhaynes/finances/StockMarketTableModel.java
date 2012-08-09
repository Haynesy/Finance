package com.adamhaynes.finances;

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

    private int startingYear;
    private int endingYear;
    private StockMarketYear[] years;

    public StockMarketTableModel(int startingYear, int endingYear, Dollars startingBalance, Dollars startingPrincipal,
                                 InterestRate interestRate, TaxRate capitalGainsTax) {
        this.startingYear = startingYear;
        this.endingYear = endingYear;
        populateYears(startingBalance, startingPrincipal, interestRate, capitalGainsTax);

    }

    private void populateYears(Dollars startingBalance, Dollars startingPrincipal,
                               InterestRate interestRate, TaxRate capitalGainsTax) {

        years = new StockMarketYear[getRowCount()];
        years[0] = new StockMarketYear(startingBalance, startingPrincipal, interestRate,capitalGainsTax);

        for(int i = 1; i < getRowCount(); i++){
            years[i] = years[i - 1].nextYear();
        }
    }

    public int getRowCount() {
        return endingYear - startingYear + 1;
    }

    public int getColumnCount() {
        return COLUMN_TITLES.length;
    }

    public String getColumnName(int index){
        return COLUMN_TITLES[index];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 0: return startingYear + rowIndex;
            case 1: return years[rowIndex].startingBalance();
            case 2: return years[rowIndex].startingPrincipal();
            case 3: return years[rowIndex].totalWithdrawn();
            case 4: return years[rowIndex].appreciation();
            case 5: return years[rowIndex].endingBalance();
            default: return "";
        }
    }
}
