package com.adamhaynes.finances;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 5/08/12
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class InterestRate {
    private double rate;

    public InterestRate(double rate) {
        this.rate = rate / 100.0;
    }

    public int interestOn(int amount) {
        return (int)(amount * rate);
    }

    @Override
    public String toString(){
        return (int)(rate * 100) + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestRate that = (InterestRate) o;

        if (Double.compare(that.rate, rate) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = rate != +0.0d ? Double.doubleToLongBits(rate) : 0L;
        return (int) (temp ^ (temp >>> 32));
    }
}
