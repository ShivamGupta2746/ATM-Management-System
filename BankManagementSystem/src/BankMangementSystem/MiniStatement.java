package BankMangementSystem;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MiniStatement extends JFrame {
    String pinnumber;
    MiniStatement(String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("Mini Statement ");
        setLayout(null);

        JLabel mini = new JLabel();
        mini.setBounds(20,140,400,200);
        add(mini);

        JLabel bank = new JLabel("Indian Bank ");
        bank.setFont(new Font("Raleway",Font.BOLD,16));
        bank.setBounds(150,20,100,20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20,80,300,20);
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);

        try {
            classforName("Com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bams";
            String Uname = "shivam";
            String Upass = "123";
            Connection con = DriverManager.getConnection(url, Uname, Upass);
            String query = "select * from login where pin ='" + pinnumber + "' ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                card.setText("Card Number:  " + rs.getString("cardnumber").substring(0,4)+"XXXXXXXX"+rs.getString("cardnumber").substring(12));
            }

        }catch (Exception e){
            System.out.println(e);
        }

        try {
            int bal =0;
            classforName("Com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bams";
            String Uname = "shivam";
            String Upass = "123";
            Connection con = DriverManager.getConnection(url, Uname, Upass);
            String query1 = "select * from bank where pin ='" + pinnumber + "' ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()){
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp " + rs.getString("amount")+ "<br><br><html>");
                if (rs.getString("type").equals("Deposit")){
                    bal +=Integer.parseInt(rs.getString("amount"));
                }else {
                    bal -=Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your Current Account Balance is Rs " + bal);
        }catch (Exception e){

        }


        setSize(400,600);
        setLocation(20,20);
//        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MiniStatement("");
    }
    private static void classforName(String args){

    }
}
