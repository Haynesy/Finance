package com.adamhaynes.finances;

/**
 * Created with IntelliJ IDEA.
 * User: Adam.Haynes
 * Date: 31/07/12
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class SavingsAccountYear {

    private int startingBalance = 0;
    private int balance = 0;
    private int interestRate = 0;
    private int capitalGainsAmount;
    private int totalWithdrawn;

    public SavingsAccountYear(int balance, int interestRate) {
        startingBalance = balance;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public SavingsAccountYear(int balance, int capitalGainsAmount, int interestRate) {
        startingBalance = balance;
        this.balance = balance;
        this.capitalGainsAmount = capitalGainsAmount;
        this.interestRate = interestRate;
    }

    public SavingsAccountYear nextYear() {
        return new SavingsAccountYear(endingBalance(), interestRate);
    }

    public int endingBalance() {
        return balance + (int)(balance * (interestRate / 100.0));
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int startingPrincipal() {
        return startingBalance - capitalGainsAmount;
    }

    public int endingPrincipal(){

        int result = startingPrincipal() - totalWithdrawn;
        return (result < 0) ? 0 : result;
    }

    public int interestRate() {
        return interestRate;
    }

    public void withdraw(int amount) {
        balance -= amount;
        totalWithdrawn = amount;

    }


}
