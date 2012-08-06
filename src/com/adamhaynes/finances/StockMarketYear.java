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
    private InterestRate interestRate;
    private int totalWithdrawals;
    private Dollars startingPrincipal;
    private TaxRate capitalGainsTaxRate;

    public StockMarketYear(Dollars startingBalance, Dollars startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.totalWithdrawals = 0;
        this.capitalGainsTaxRate = capitalGainsTaxRate;
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(new Dollars(endingBalance()), startingPrincipal(), interestRate(), capitalGainsTaxRate());
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


    public void withdraw(int amount) {
        totalWithdrawals += amount;
    }

    public int totalWithdrawn() {
        return totalWithdrawals + capitalGainsTaxIncurred();

    }

    public int endingBalance() {
        int result = startingBalance.amount() - totalWithdrawn();

        return result + interestEarned();
    }

    public int interestEarned() {
        return interestRate.interestOn(startingBalance().amount() - totalWithdrawn());
    }

    public int endingPrincipal(){

        int result = startingPrincipal().amount() - totalWithdrawals;
        return Math.max(0, result);
    }

    private int capitalGainsWithdrawn() {
        int result = -1 * startingPrincipal().subtract(new Dollars(totalWithdrawals)).amount();
        return Math.max(0, result);
    }

    public int capitalGainsTaxIncurred() {
        return capitalGainsTaxRate().compoundTaxFor(capitalGainsWithdrawn());
    }
}
