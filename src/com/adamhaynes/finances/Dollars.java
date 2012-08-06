package com.adamhaynes.finances;

/**
 *
 * User: Haynesy
 * Date: 5/08/12
 * Time: 6:13 PM
 *
 */
public class Dollars {

    private int amount;

    public Dollars(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "$"+ amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if(object == null) return false;
        if (getClass() != object.getClass()) return false;

        Dollars dollars = (Dollars) object;
        if (amount != dollars.amount) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return amount;
    }

    public Dollars add(Dollars dollars) {
        return new Dollars(this.amount + dollars.amount);
    }

    public Dollars subtract(Dollars dollars) {
        return new Dollars(this.amount - dollars.amount);
    }

    public Dollars subtractToZero(Dollars dollars) {
        int result = this.amount - dollars.amount;
        return new Dollars(Math.max(0, result));
    }
}
