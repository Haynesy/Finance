package com.adamhaynes.finances.domain;

/**
 *
 * User: Haynesy
 * Date: 5/08/12
 * Time: 6:13 PM
 *
 */
public class Dollars {

    private double amount;

    public Dollars(int amount) {
        this.amount = amount;
    }

    public Dollars(double amount) {
        this.amount = amount;
    }

    public Dollars plus(Dollars dollars) {
        return new Dollars(this.amount + dollars.amount);
    }

    public Dollars minus(Dollars dollars) {
        return new Dollars(this.amount - dollars.amount);
    }

    public Dollars subtractToZero(Dollars dollars) {
        double result = this.amount - dollars.amount;
        return new Dollars(Math.max(0, result));
    }

    public Dollars percentage(double percentage) {
        double result = amount * (percentage / 100.0);
        return new Dollars(result);
    }

    private long roundOffCents() {
        return Math.round(amount);
    }

    @Override
    public String toString(){
        return "$"+ roundOffCents();
    }

    @Override
    public boolean equals(Object object) {

        Dollars dollars = (Dollars) object;
        if (roundOffCents() != dollars.roundOffCents())
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int)roundOffCents();
    }


    public Dollars minOfTwoValues(Dollars that) {
        return new Dollars(Math.min(this.amount, that.amount));
    }
}
