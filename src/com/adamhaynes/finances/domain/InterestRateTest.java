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
public class InterestRateTest {

    @Test
    public void interest(){
        InterestRate rate = new InterestRate(10);
        assertEquals(new Dollars(100), rate.interestOn(new Dollars(1000)));
    }

    @Test
    public void toInt(){
        assertEquals(10, new Dollars(10).toInt());
    }

    @Test
    public void objectValue(){
        InterestRate rate1a = new InterestRate(33);
        InterestRate rate1b = new InterestRate(33);
        InterestRate rate2 = new InterestRate(40);

        assertEquals("33%", rate1a.toString());
        assertTrue("rate value the same, should be true", rate1a.equals(rate1b));
        assertFalse("rate values different, should be false ", rate1a.equals(rate2));
        assertTrue("rate values are the same, hash code should be equal", rate1a.hashCode() == rate1b.hashCode());

    }
}
