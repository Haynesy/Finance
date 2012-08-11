package com.adamhaynes.finances.domain;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 *
 * User: Haynesy
 * Date: 5/08/12
 * Time: 6:06 PM
 *
 */
public class DollarsTest {

    @Test
    public void addition(){
        assertEquals(new Dollars(3), new Dollars(1).plus(new Dollars(2)));
    }

    @Test
    public void subtraction(){
        assertEquals("positive result", new Dollars(10), new Dollars(20).minus(new Dollars(10)));
        assertEquals("negative result", new Dollars(-1), new Dollars(1).minus(new Dollars(2)));
    }

    @Test
    public void minusToZero(){
        assertEquals("positive result", new Dollars(10), new Dollars(20).subtractToZero(new Dollars(10)));
        assertEquals("zero result", new Dollars(0), new Dollars(1).subtractToZero(new Dollars(100)));
    }

    @Test
    public void percentage(){
        assertEquals(new Dollars(2), new Dollars(10).percentage(20));
    }

    @Test
    public void equalsIgnoresCents(){
        assertTrue("should round down", new Dollars(10).equals(new Dollars(10.10)));
        assertTrue("should round up", new Dollars(10).equals(new Dollars(9.90)));
        assertTrue("should round up when we have 50 cents", new Dollars(2).equals(new Dollars(1.50)));
    }

    @Test
    public void hashCodeIgnoresCents(){
        assertTrue("cents should not effect hash code value",
                new Dollars(10).hashCode() == new Dollars(10.10).hashCode());
        assertTrue("should round up to equal same hash code",
                new Dollars(10).hashCode() ==  new Dollars(9.90).hashCode());
        assertTrue("should round up when we have 50 cents to equal same hash code",
                new Dollars(2).hashCode() == new Dollars(1.50).hashCode());
    }

    @Test
    public void toStringIgnoresCents(){
        assertEquals("should round down", "$10", new Dollars(10.10).toString());
        assertEquals("should round up", "$10", new Dollars(9.90).toString());
        assertEquals("should round up when we have 50 cents", "$2", new Dollars(1.50).toString());
    }

    @Test
    public void dollarsMax(){
        Dollars value1 = new Dollars(2);
        Dollars value2 = new Dollars(1);

        assertEquals("value 1", new Dollars(1), value1.minOfTwoValues(value2));
        assertEquals("value 2", new Dollars(1), value2.minOfTwoValues(value1));
    }

    @Test
    public void valueObject(){
        Dollars dollars1a = new Dollars(33);
        Dollars dollars1b = new Dollars(33);
        Dollars dollars2 = new Dollars(100);

        assertEquals("$33", dollars1a.toString());
        assertTrue("values are equal, should be true", dollars1a.equals(dollars1b));
        assertFalse("values are not equal, should be false", dollars1a.equals(dollars2));
        assertTrue("values are equal, hash code should be equal", dollars1a.hashCode() == dollars1b.hashCode());
    }
}
