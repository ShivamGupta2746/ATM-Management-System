package BankMangementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton back;
    String pinnumber;
    BalanceEnquiry(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("image/ATMScreen.png"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image .setBounds(0,0,900,900);
        add(image);

        back = new JButton("Back");
        back.setBounds(340,310,150,30);
        back.setFont(new Font("Raleway",Font.BOLD,16));
        back.addActionListener(this);
        image.add(back);
        int balance = 0;
        try {

            classforName("Com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bams";
            String Uname = "shivam";
            String Upass = "123";
            Connection con = DriverManager.getConnection(url, Uname, Upass);
            String query = "select * from bank where pin ='" + pinnumber + "' ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

        JLabel text = new JLabel("Your Current Balance Is Rs  " +balance);
        text.setForeground(Color.WHITE);
        text.setBounds(200,150,400,30);
        text.setFont(new Font("Raleway",Font.BOLD,16));
        image.add(text);



            setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);

    }
    public static void main(String[] args) {
        new  BalanceEnquiry("");

    }
    private static void classforName(String args){

    }
}
