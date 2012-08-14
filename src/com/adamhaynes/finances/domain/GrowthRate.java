package com.adamhaynes.finances.domain;

import com.adamhaynes.finances.util.Require;

/**
 *
 * User: Haynesy
 * Date: 5/08/12
 * Time: 4:14 PM
 *
 */
public class GrowthRate {
    private double rate;

    public GrowthRate(double rate) {
        Require.that(rate > 0, "Interest rate must be positive (and not zero); was " + rate);
        this.rate = rate;
    }

    public Dollars growthFor(Dollars dollars) {
        return dollars.percentage(rate);
    }

    @Override
    public String toString(){
        return rate + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrowthRate that = (GrowthRate) o;

        if (Double.compare(that.rate, rate) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = rate != +0.0d ? Double.doubleToLongBits(rate) : 0L;
        return (int) (temp ^ (temp >>> 32));
    }
}
