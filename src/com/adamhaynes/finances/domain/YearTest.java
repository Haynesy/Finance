package com.adamhaynes.finances.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 10/08/12
 * Time: 2:11 PM
 */
public class YearTest {

    @Test
    public void nextYear(){
        assertEquals(new Year(2011), new Year(2010).nextYear());
    }

    @Test
    public void numberOfYearsInclusive(){
        assertEquals(41, new Year(2010).numberOfYearsInclusive(new Year(2050)));
    }


    @Test
    public void valueObject() {
        Year year1a = new Year(2010);
        Year year1b = new Year(2010);
        Year year2 = new Year(2050);

        assertEquals("2010", year1a.toString());
        assertTrue("values are equal, should be true", year1a.equals(year1b));
        assertFalse("values are not equal, should be false", year1a.equals(year2));
        assertTrue("hash code should be equal", year1a.hashCode() == year1b.hashCode());
    }
}
