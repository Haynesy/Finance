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

    public SavingsAccountYear(int balance, int interestRate) {
        startingBalance = balance;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public SavingsAccountYear() {

    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int balance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public SavingsAccountYear nextYear() {

        return new SavingsAccountYear(this.endingBalance(), interestRate);
    }

    public int endingBalance() {

        return balance() + (int)(balance() * (interestRate / 100.0));
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int interestRate() {
        return interestRate;
    }
}
