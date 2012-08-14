package com.adamhaynes.finances.domain;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 *
 * User: Haynesy
 * Date: 5/08/12
 * Time: 4:13 PM
 *
 */
public class GrowthRateTest {

    @Test
    public void interest(){
        GrowthRate rate = new GrowthRate(10);
        assertEquals(new Dollars(100), rate.growthFor(new Dollars(1000)));
    }

    @Test
    public void objectValue(){
        GrowthRate rate1a = new GrowthRate(33);
        GrowthRate rate1b = new GrowthRate(33);
        GrowthRate rate2 = new GrowthRate(40);

        assertEquals("33.0%", rate1a.toString());
        assertTrue("rate value the same, should be true", rate1a.equals(rate1b));
        assertFalse("rate values different, should be false ", rate1a.equals(rate2));
        assertTrue("rate values are the same, hash code should be equal", rate1a.hashCode() == rate1b.hashCode());

    }
}
