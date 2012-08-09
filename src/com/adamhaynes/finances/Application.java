package com.adamhaynes.finances;

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

        StockMarketTableModel model = new StockMarketTableModel(2010, 2050, new Dollars(10000), new Dollars(7000),
                new InterestRate(10), new TaxRate(25));
        JTable table = new JTable(model);

        return new JScrollPane(table);
    }

    public static void main(String[] args){
        new Application().setVisible(true);
        System.out.println("Foo!");

    }
}
