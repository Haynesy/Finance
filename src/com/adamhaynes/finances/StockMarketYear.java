package com.adamhaynes.finances;

/**
 * Created with IntelliJ IDEA.
 * User: Adam.Haynes
 * Date: 31/07/12
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockMarketYear {

    private int startingBalance;
    private int interestRate;
    private int totalWithdrawals;
    private int startingPrincipal;
    private int capitalGainsTaxRate;

    public StockMarketYear(int startingBalance, int startingPrincipal, int interestRate, int capitalGainsTaxRate) {
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

    public int interestRate() {
        return interestRate;
    }

    public int capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }


    public void withdraw(int amount) {
        totalWithdrawals += amount;
    }

    public int totalWithdrawn() {
        return totalWithdrawals + capitalGainsTaxIncurred();

    }

    public int endingBalance() {
        int result = startingBalance - totalWithdrawals - capitalGainsTaxIncurred();

        return result + interestEarned();
    }

    public int interestEarned() {
        return (startingBalance() - totalWithdrawals - capitalGainsTaxIncurred())* interestRate / 100;
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
        double capitalGainsWithdrawn = (double)capitalGainsWithdrawn();
        double inverseTaxRateApplied = 1 - (capitalGainsTaxRate() / 100.0);
        return (int)(capitalGainsWithdrawn / inverseTaxRateApplied - capitalGainsWithdrawn);
    }
}
