package com.adamhaynes.finances.domain;

import com.adamhaynes.finances.util.Require;

/**
 *
 * User: Haynesy
 * Date: 5/08/12
 * Time: 12:11 PM
 *
 */
public class TaxRate {
    private double rate;

    public TaxRate(double taxRate) {
        Require.that(taxRate > 0, "Tax rate must be positive (and not zero); was "+ taxRate);
        rate = taxRate;
    }

    public Dollars taxFor(Dollars dollars) {

        Dollars temp =  dollars.percentage(rate);
        return temp;
    }

    public Dollars compoundTaxFor(Dollars dollars) {

        double percentage = rate / 100.0;
        percentage = (1 / (1 - percentage)) - 1;

        return dollars.percentage(percentage * 100.0);
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

        return (int)rate + "%";
    }
}
