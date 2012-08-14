package com.adamhaynes.finances.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 *
 * User: Adam.Haynes
 * Date: 31/07/12
 * Time: 12:23 PM
 *
 */
public class StockMarketYearTest {

    // Starting stock market values
    private static final Dollars STARTING_BALANCE = new Dollars(10000);
    private static final Dollars STARTING_PRINCIPAL = new Dollars(3000);
    private static final GrowthRate GROWTH_RATE = new GrowthRate(10);
    private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);
    private static final Year YEAR = new Year(2010);

    private StockMarketYear newYear(){
        return new StockMarketYear(YEAR, STARTING_BALANCE, STARTING_PRINCIPAL,
                GROWTH_RATE, CAPITAL_GAINS_TAX_RATE);
    }

    @Test
    public void startingValues(){

        StockMarketYear year = newYear();
        assertEquals("balance", STARTING_BALANCE, year.startingBalance());
        assertEquals("principal", STARTING_PRINCIPAL, year.startingCostBasis());
        assertEquals("interest", GROWTH_RATE, year.growthRate());
        assertEquals("tax rate", CAPITAL_GAINS_TAX_RATE, year.capitalGainsTaxRate());
        assertEquals("total withdrawn default", new Dollars(0), year.totalSold());
        assertEquals("year", YEAR, year.year());
    }

    @Test
    public void totalSold(){
        StockMarketYear year = newYear();
        assertEquals("no sales", new Dollars(0), year.totalSellOrders());
        year.sell(new Dollars(3000));
        assertEquals("one sale", new Dollars(3000), year.totalSellOrders());
        year.sell(new Dollars(750));
        assertEquals("multiple sales", new Dollars(3750), year.totalSellOrders());
    }

    @Test
    public void capitalGainsTax(){
        StockMarketYear year = newYear();
        year.sell(new Dollars(4000));
        int capitalGainsTax = 250;
        int withdrawalsToCoverCapitalGainsTax = 83;

        assertEquals("capital gains tax includes tax on withdrawals to cover capital gains",
                new Dollars(1000 + capitalGainsTax + withdrawalsToCoverCapitalGainsTax),
                year.capitalGainsTaxIncurred());

        assertEquals("total withdrawn includes capital gains tax",
                new Dollars(5000 + capitalGainsTax + withdrawalsToCoverCapitalGainsTax),
                year.totalSold());
    }

    @Test
    public void treatAllWithdrawalsAsSubjectToTaxUntilAllCapitalGainsHasBeenWithdrawn(){
        StockMarketYear year = newYear();
        Dollars capitalGains = STARTING_BALANCE.minus(STARTING_PRINCIPAL);

        year.sell(new Dollars(500));
        assertEquals("pay tax on entire withdrawal",
                        new Dollars(167), year.capitalGainsTaxIncurred());

        year.sell(capitalGains);
        assertEquals("pay compounding tax on capital gains even though that pays extra tax",
                new Dollars(2333), year.capitalGainsTaxIncurred());

        year.sell(new Dollars(1000));
        assertEquals("pay no more tax once all capital gains withdrawn",
                new Dollars(2333), year.capitalGainsTaxIncurred());
    }

    @Test
    public void interestEarned(){
        StockMarketYear year = newYear();
        assertEquals("basic interest earned", new Dollars(1000), year.growth());
        year.sell(new Dollars(2000));
        assertEquals("withdrawals (which pay capital gains tax) don't earn interest", new Dollars(733), year.growth());
    }

    @Test
    public void endingPrincipal(){
        StockMarketYear year = newYear();

        year.sell(new Dollars(500));
        assertEquals("withdrawals less than capital gains do not reduce principal", STARTING_PRINCIPAL, year.endingCostBasis());

        year.sell(new Dollars(6500));
        assertEquals("total withdrawals", new Dollars(9333), year.totalSold());

        Dollars totalWithdrawn = new Dollars(9333);
        Dollars capitalGains = new Dollars(7000);
        Dollars principalReduced = totalWithdrawn.minus(capitalGains);
        Dollars expectedPrincipal = STARTING_PRINCIPAL.minus(principalReduced);

        assertEquals("principal should be reduced by the difference in total withdrawals and capital gains",
                expectedPrincipal, year.endingCostBasis());

        year.sell(new Dollars(1000)); // Last 1000 not added to capital gains so no tax applies
        assertEquals("principal will go negative when overdrawn", new Dollars(-333), year.endingCostBasis());
    }

    @Test
    public void endingBalance(){
        StockMarketYear year = newYear();
        assertEquals("ending balance includes interest", new Dollars(11000), year.endingBalance());
        year.sell(new Dollars(1000));
        assertEquals("ending balance includes withdrawals (which pay capital gains tax) and interest",
                new Dollars(9533), year.endingBalance());
    }

    @Test
    public void stockMarketYearKnowsWhichYearItRepresents(){
        StockMarketYear market = newYear();
        assertEquals(new Year(2010), market.year());
    }

    @Test
    public void nextYearStaringValuesMatchesThisYearsEndingValues(){
        StockMarketYear year = newYear();
        assertEquals("balance", year.endingBalance(), year.nextYear().startingBalance());
        assertEquals("principal", year.endingCostBasis(), year.nextYear().startingCostBasis());
        assertEquals("interest", year.growthRate(), year.nextYear().growthRate());
        assertEquals("capital gains tax", year.capitalGainsTaxRate(), year.nextYear().capitalGainsTaxRate());
        assertEquals("year", new Year(2011), year.nextYear().year());
    }
}
