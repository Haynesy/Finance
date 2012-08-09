package com.adamhaynes.finances;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 9/08/12
 * Time: 2:03 PM
 */
public class StockMarket {
    private int startingYear;
    private int endingYear;

    private Dollars startingBalance;
    private Dollars startingPrincipal;
    private InterestRate interestRate;
    private TaxRate capitalGainsTax;

    public StockMarket(int startingYear, int endingYear, Dollars startingBalance, Dollars startingPrincipal,
                                   InterestRate interestRate, TaxRate capitalGainsTax) {
        this.startingYear = startingYear;
        this.endingYear = endingYear;
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.capitalGainsTax = capitalGainsTax;
    }

    public StockMarketYear getYear(int offset) {
        StockMarketYear year = new StockMarketYear(startingBalance, startingPrincipal, interestRate, capitalGainsTax);
        for(int i = 0; i < offset; i++){
            year = year.nextYear();
        }
        return year;
    }

    public int numberOfYears() {
        return endingYear - startingYear + 1;
    }
}
