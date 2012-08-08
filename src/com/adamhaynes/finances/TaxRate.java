package com.adamhaynes.finances;

/**
 *
 * User: Haynesy
 * Date: 5/08/12
 * Time: 12:11 PM
 *
 */
public class TaxRate {
    private double rate;

    public TaxRate(double taxRateAsPercentage) {
        rate = taxRateAsPercentage / 100.0;
    }

    public Dollars taxFor(Dollars dollars) {
        return new Dollars((int)(rate * dollars.toInt()));
    }

    public Dollars compoundTaxFor(Dollars dollars) {

        return new Dollars((int)(dollars.toInt() / (1 - rate)) - dollars.toInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxRate taxRate = (TaxRate) o;

        if (Double.compare(taxRate.rate, rate) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = rate != +0.0d ? Double.doubleToLongBits(rate) : 0L;
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString(){

        return (int)(rate * 100) + "%";
    }
}
