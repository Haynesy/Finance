package com.adamhaynes.finances;

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
        assertEquals(new Dollars(3), new Dollars(1).add(new Dollars(2)));
    }

    @Test
    public void subtraction(){
        assertEquals("positive result", new Dollars(10), new Dollars(20).subtract(new Dollars(10)));
        assertEquals("negative result", new Dollars(-1), new Dollars(1).subtract(new Dollars(2)));
    }

    @Test
    public void subtractToZero(){
        assertEquals("positive result", new Dollars(10), new Dollars(20).subtractToZero(new Dollars(10)));
        assertEquals("zero result", new Dollars(0), new Dollars(1).subtractToZero(new Dollars(100)));
    }

    @Test
    public void toInt(){
        assertEquals(20, new Dollars(20).toInt());
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
