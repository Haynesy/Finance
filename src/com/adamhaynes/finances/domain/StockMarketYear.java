package com.adamhaynes.finances.domain;

/**
 *
 * User: Adam.Haynes
 * Date: 31/07/12
 * Time: 12:27 PM
 *
 */
public class StockMarketYear {

    private Dollars startingBalance;
    private Dollars startingPrincipal;
    private InterestRate interestRate;
    private Dollars totalSales;
    private TaxRate capitalGainsTaxRate;
    private Year year;

    public StockMarketYear(Year year, Dollars startingBalance, Dollars startingPrincipal,
                           InterestRate interestRate, TaxRate capitalGainsTaxRate) {

        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.totalSales = new Dollars(0);
        this.capitalGainsTaxRate = capitalGainsTaxRate;
        this.year = year;
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(year.nextYear(), endingBalance(), startingPrincipal(),
                interestRate(), capitalGainsTaxRate());
    }

    public Dollars startingBalance() {
        return startingBalance;
    }

    public Dollars startingPrincipal() {
        return startingPrincipal;
    }

    public InterestRate interestRate() {
        return interestRate;
    }

    public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }


    public void sell(Dollars amount) {
        totalSales = totalSales().plus(amount);
    }

    public Dollars totalWithdrawn() {
        return totalSales().plus(capitalGainsTaxIncurred());
    }

    public Dollars endingBalance() {
        return startingBalance.minus(totalWithdrawn()).plus(appreciation());
    }

    // Interest earned
    public Dollars appreciation() {
        return interestRate.interestOn(startingBalance().minus(totalWithdrawn()));
    }

    public Dollars endingPrincipal(){
        Dollars dollarsPrincipalReducedBy = totalWithdrawn().subtractToZero(startingCapitalGains());
        return startingPrincipal.minus(dollarsPrincipalReducedBy);
    }

    private Dollars startingCapitalGains() {
        return startingBalance().minus(startingPrincipal());
    }

    private Dollars capitalGainsWithdrawn() {
        return Dollars.min(startingCapitalGains(), totalSales());
    }


    public Dollars capitalGainsTaxIncurred() {
        return capitalGainsTaxRate().compoundTaxFor(capitalGainsWithdrawn());
    }

    public Year year() {
        return year;
    }

    public Dollars totalSales() {
        return totalSales;
    }
}
