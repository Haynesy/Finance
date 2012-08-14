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
    private Dollars costBasis;
    private GrowthRate growthRate;
    private Dollars totalSellOrders;
    private TaxRate capitalGainsTaxRate;
    private Year year;

    public StockMarketYear(Year year, Dollars startingBalance, Dollars costBasis,
                           GrowthRate growthRate, TaxRate capitalGainsTaxRate) {

        this.startingBalance = startingBalance;
        this.costBasis = costBasis;
        this.growthRate = growthRate;
        this.totalSellOrders = new Dollars(0);
        this.capitalGainsTaxRate = capitalGainsTaxRate;
        this.year = year;
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(year.nextYear(), endingBalance(), startingCostBasis(),
                growthRate(), capitalGainsTaxRate());
    }

    public Dollars startingBalance() {
        return startingBalance;
    }

    public Dollars startingCostBasis() {
        return costBasis;
    }

    public GrowthRate growthRate() {
        return growthRate;
    }

    public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }

    public Year year() {
        return year;
    }

    public Dollars totalSellOrders() {
        return totalSellOrders;
    }

    public void sell(Dollars amount) {
        totalSellOrders = totalSellOrders().plus(amount);
    }

    public Dollars totalSold() {
        return totalSellOrders().plus(capitalGainsTaxIncurred());
    }

    public Dollars endingBalance() {
        return startingBalance().minus(totalSold()).plus(growth());
    }

    // Interest earned
    public Dollars growth() {
        return growthRate().growthFor(startingBalance().minus(totalSold()));
    }

    public Dollars endingCostBasis(){
        Dollars purchasesSold = totalSold().subtractToZero(startingCapitalGains());
        return startingCostBasis().minus(purchasesSold);
    }

    private Dollars startingCapitalGains() {
        return startingBalance().minus(startingCostBasis());
    }

    private Dollars capitalGainsWithdrawn() {
        return Dollars.min(startingCapitalGains(), totalSellOrders());
    }


    public Dollars capitalGainsTaxIncurred() {
        return capitalGainsTaxRate().compoundTaxFor(capitalGainsWithdrawn());
    }
}
