package com.adamhaynes.finances.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 9/08/12
 * Time: 2:03 PM
 */
public class StockMarket {

    private Year startingYear;
    private Year endingYear;
    private StockMarketYear[] years;

    public StockMarket(Year startingYear, Year endingYear, Dollars startingBalance, Dollars startingPrincipal,
                                   InterestRate interestRate, TaxRate capitalGainsTax) {
        this.startingYear = startingYear;
        this.endingYear = endingYear;

        populateYears(startingBalance, startingPrincipal, interestRate, capitalGainsTax, startingYear);
    }

    private void populateYears(Dollars startingBalance, Dollars startingPrincipal,
                               InterestRate interestRate, TaxRate capitalGainsTax,
                               Year startingYear) {

        years = new StockMarketYear[numberOfYears()];
        years[0] = new StockMarketYear(startingYear, startingBalance, startingPrincipal, interestRate,
                capitalGainsTax);

        for(int i = 1; i < numberOfYears(); i++){
            years[i] = years[i - 1].nextYear();
        }
    }

    public StockMarketYear getYearOffset(int offset) {
        return years[offset];
    }

    public int numberOfYears() {
        return startingYear.numberOfYearsInclusive(endingYear);
    }
}
