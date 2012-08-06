package com.adamhaynes.finances;

/**
 *
 * User: Adam.Haynes
 * Date: 31/07/12
 * Time: 12:27 PM
 *
 */
public class StockMarketYear {

    private int startingBalance;
    private InterestRate interestRate;
    private int totalWithdrawals;
    private int startingPrincipal;
    private TaxRate capitalGainsTaxRate;

    public StockMarketYear(int startingBalance, int startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.totalWithdrawals = 0;
        this.capitalGainsTaxRate = capitalGainsTaxRate;
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(endingBalance(), startingPrincipal(), interestRate(), capitalGainsTaxRate());
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int startingPrincipal() {
        return startingPrincipal;
    }

    public InterestRate interestRate() {
        return interestRate;
    }

    public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }


    public void withdraw(int amount) {
        totalWithdrawals += amount;
    }

    public int totalWithdrawn() {
        return totalWithdrawals + capitalGainsTaxIncurred();

    }

    public int endingBalance() {
        int result = startingBalance - totalWithdrawn();

        return result + interestEarned();
    }

    public int interestEarned() {
        return interestRate.interestOn(startingBalance() - totalWithdrawn());
    }

    public int endingPrincipal(){

        int result = startingPrincipal() - totalWithdrawals;
        return Math.max(0, result);
    }

    private int capitalGainsWithdrawn() {
        int result = -1 * (startingPrincipal() - totalWithdrawals);
        return Math.max(0, result);
    }

    public int capitalGainsTaxIncurred() {
        return capitalGainsTaxRate().compoundTaxFor(capitalGainsWithdrawn());
    }
}
