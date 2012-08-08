package com.adamhaynes.finances;

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

    public StockMarketYear(Dollars startingBalance, Dollars startingPrincipal,
                           InterestRate interestRate, TaxRate capitalGainsTaxRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.totalWithdrawals = new Dollars(0);
        this.capitalGainsTaxRate = capitalGainsTaxRate;
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(endingBalance(), startingPrincipal(),
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
        totalWithdrawals = totalWithdrawals.add(amount);
    }

    public Dollars totalWithdrawn() {
        return totalWithdrawals.add(capitalGainsTaxIncurred());
    }

    public Dollars endingBalance() {
        return startingBalance.subtract(totalWithdrawn()).add(interestEarned());
    }

    public Dollars interestEarned() {
        return interestRate.interestOn(startingBalance().subtract(totalWithdrawn()));
    }

    public Dollars endingPrincipal(){

        return startingPrincipal().subtractToZero(totalWithdrawals);
    }

    private Dollars capitalGainsWithdrawn() {

        return totalWithdrawals.subtractToZero(startingPrincipal());
    }

    public Dollars capitalGainsTaxIncurred() {
        return capitalGainsTaxRate().compoundTaxFor(capitalGainsWithdrawn());
    }
}
