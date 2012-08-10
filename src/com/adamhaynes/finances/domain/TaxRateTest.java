package com.adamhaynes.finances.domain;


import org.junit.Test;

import static junit.framework.Assert.*;

/**
 *
 * User: Haynesy
 * Date: 5/08/12
 * Time: 12:06 PM
 *
 */
public class TaxRateTest {

    @Test
    public void simpleTaxJustAppliesTaxRateToAmount(){
        TaxRate taxRate = new TaxRate(25);
        assertEquals(new Dollars(250), taxRate.taxFor(new Dollars(1000)));
    }

    @Test
    public void compoundTaxIsTheAmountOfTaxThatIsIncurredIfYouAlsoPayTaxOnTheTax(){
        TaxRate taxRate = new TaxRate(25);
        assertEquals(new Dollars(333), taxRate.compoundTaxFor(new Dollars(1000)));
    }

    @Test
    public void valueObject(){
        TaxRate rate1a = new TaxRate(33);
        TaxRate rate1b = new TaxRate(33);
        TaxRate rate2 = new TaxRate(40);

        assertEquals("33%", rate1a.toString());
        assertTrue("same values should be true", rate1a.equals(rate1b));
        assertFalse("different values should be false", rate1a.equals(rate2));
        assertTrue("same values have same hash code", rate1a.hashCode() == rate1b.hashCode());

    }

}
