package com.adamhaynes.finances;

import com.adamhaynes.finances.domain.*;
import com.adamhaynes.finances.ui.StockMarketTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 8/08/12
 * Time: 4:50 PM
 */
public class Application extends JFrame {

    public Application(){
        setSize(900, 400);
        setLocation(400, 300);

        Container content = getContentPane();
        content.add(table());
    }

    private JScrollPane table() {

        StockMarketTableModel model = new StockMarketTableModel(stockMarket());
        JTable table = new JTable(model);

        return new JScrollPane(table);
    }

    private StockMarket stockMarket() {
        Year startingYear = new Year(2010);
        Year endingYear = new Year(2050);
        Dollars startingBalance = new Dollars(10000);
        Dollars startingPrincipal = new Dollars(7000);
        InterestRate interestRate = new InterestRate(10);
        TaxRate capitalGainsTax = new TaxRate(25);

        return new StockMarket(startingYear, endingYear,
                startingBalance, startingPrincipal, interestRate,
                capitalGainsTax, new Dollars(10));
    }

    public static void main(String[] args){
        new Application().setVisible(true);
    }
}
