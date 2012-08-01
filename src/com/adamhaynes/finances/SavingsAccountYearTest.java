package com.adamhaynes.finances;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Adam.Haynes
 * Date: 31/07/12
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SavingsAccountYearTest{

    @Test
    public void startingBalance(){

        // Arrange
        SavingsAccountYear account = newAccount(10000, 10);

        // Assert
        assertEquals(10000, account.startingBalance());
    }

    @Test
    public void interestRate(){

        // Arrange
        SavingsAccountYear account = newAccount(10000, 10);

        // Assert
        assertEquals(10, account.interestRate());
    }

    @Test
    public void endingBalance(){

        // Arrange
        SavingsAccountYear account = newAccount(10000, 10);

        // Assert
        assertEquals(11000, account.endingBalance());
    }

    @Test
    public void nextYear(){

        // Arrange
        SavingsAccountYear thisYear = newAccount();

        // Assert
        assertEquals(thisYear.endingBalance(), thisYear.nextYear().startingBalance());
    }

    @Test
    public void nextYearsInterestRateIsEqualToNextYearsInterestRate(){

        // Arrange
        SavingsAccountYear thisYear = newAccount();

        // Assert
        assertEquals(thisYear.interestRate(), thisYear.nextYear().interestRate());

    }

    @Test
    public void withdrawingFundsOccursAtTheBeginningOfTheYear(){

        // Arrange
        SavingsAccountYear account = newAccount(10000, 10);

        // Act
        account.withdraw(1000);

        // Assert
        assertEquals(9000 + 900, account.endingBalance());

    }

    @Test
        public void multileWithdrawlsInAYear(){
            // Arrange
            SavingsAccountYear account = newAccount(10000, 10);

            // Act
            account.withdraw(1000);
            account.withdraw(2000);


            // Assert
            assertEquals(7000 + 700, account.endingBalance());

        }


    @Test
    public void startingPrincipal(){

        // Arrange
        SavingsAccountYear account = new SavingsAccountYear(10000, 7000, 10);

        // Assert
        assertEquals(3000, account.startingPrincipal());
    }

    @Test
    public void endingPrincipal(){

        // Arrange
        SavingsAccountYear account = new SavingsAccountYear(10000, 7000, 10);
        assertEquals("starting principal", 3000, account.startingPrincipal());

        // Act
        account.withdraw(2000);

        // Assert
        assertEquals("ending principal", 1000, account.endingPrincipal());
    }

    @Test
    public void endingPrincipalNeverGoesBelowZero(){

        // Arrange
        int anyNumberLargerThanPrincipal = 4000;
        SavingsAccountYear account = new SavingsAccountYear(10000, 7000, 10);

        // Act
        account.withdraw(anyNumberLargerThanPrincipal);

        // Assert
        assertEquals("ending principal", 0, account.endingPrincipal());
    }

//    @Test
//    public void withdrawingMoreThanPrincipalIncursCapitalGainsTax()
//    {
//        SavingsAccountYear account = new SavingsAccountYear(10000, 7000, 10);
//        account.withdraw(3000);
//        assertEquals(7000 + 700, account.endingBalance());
//        account.withdraw(5000);
//        assertEquals(2000 + 200 - (5000 * 0.25), account.endingBalance());
//    }

    /// Helper methods
    private SavingsAccountYear newAccount(){
        return new SavingsAccountYear(10000, 10);
    }

    private SavingsAccountYear newAccount(int balance, int interestRate) {
        return new SavingsAccountYear(balance, interestRate);
    }
}
