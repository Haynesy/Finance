package com.adamhaynes.finances.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 10/08/12
 * Time: 2:12 PM
 */
public class Year {
    private int year;

    public Year(int year) {
        this.year = year;
    }

    @Override
    public String toString(){
        return ""+ year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Year year1 = (Year) o;

        if (year != year1.year) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return year;
    }

    public Year nextYear() {
        return new Year(year + 1);

    }

    public int toInt() {
        return year;
    }
}
