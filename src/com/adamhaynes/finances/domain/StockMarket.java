package com.adamhaynes.finances.domain;

import com.adamhaynes.finances.util.Require;

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

    public StockMarket(Year startingYear, Year endingYear, Dollars startingBalance,
                       Dollars startingPrincipal, InterestRate interestRate,
                       TaxRate capitalGainsTax, Dollars sellEveryYear) {

        this.startingYear = startingYear;
        this.endingYear = endingYear;

        populateYears(startingBalance, startingPrincipal, interestRate,
                capitalGainsTax, startingYear, sellEveryYear);
    }

    private void populateYears(Dollars startingBalance, Dollars startingPrincipal,
                               InterestRate interestRate, TaxRate capitalGainsTax,
                               Year startingYear, Dollars sellEveryYear) {

        years = new StockMarketYear[numberOfYears()];
        years[0] = new StockMarketYear(startingYear, startingBalance,
                startingPrincipal, interestRate, capitalGainsTax);
        years[0].sell(sellEveryYear);
        for(int i = 1; i < numberOfYears(); i++){
            years[i] = years[i - 1].nextYear();
            years[i].sell(sellEveryYear);
        }
    }

    public StockMarketYear getYearOffset(int offset) {

        Require.that(offset >= 0 && offset < numberOfYears(),
                "Offset needs to be between 0 and " + (numberOfYears() - 1) + "; was "+ offset);

        return years[offset];
    }

    public int numberOfYears() {
        return startingYear.numberOfYearsInclusive(endingYear);
    }
}
