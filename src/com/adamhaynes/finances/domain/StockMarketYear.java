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
    private Dollars totalWithdrawals;
    private TaxRate capitalGainsTaxRate;
    private Year year;

    public StockMarketYear(Year year, Dollars startingBalance, Dollars startingPrincipal,
                           InterestRate interestRate, TaxRate capitalGainsTaxRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.totalWithdrawals = new Dollars(0);
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


    public void withdraw(Dollars amount) {
        totalWithdrawals = totalWithdrawals.plus(amount);
    }

    public Dollars totalWithdrawn() {
        return totalWithdrawals.plus(capitalGainsTaxIncurred());
    }

    public Dollars endingBalance() {
        return startingBalance.minus(totalWithdrawn()).plus(appreciation());
    }

    // Interest earned
    public Dollars appreciation() {
        return interestRate.interestOn(startingBalance().minus(totalWithdrawn()));
    }

    public Dollars endingPrincipal(){

        return startingPrincipal().subtractToZero(totalWithdrawals);
    }

    private Dollars capitalGainsWithdrawn() {
        Dollars capitalGains = startingBalance.minus(startingPrincipal());
        //return totalWithdrawals.subtractToZero(capitalGains);
        return capitalGains.minOfTwoValues(totalWithdrawals);

        //return totalWithdrawals.subtractToZero(startingPrincipal());
    }


    public Dollars capitalGainsTaxIncurred() {
        return capitalGainsTaxRate().compoundTaxFor(capitalGainsWithdrawn());
    }

    public Year year() {
        return year;
    }
}
