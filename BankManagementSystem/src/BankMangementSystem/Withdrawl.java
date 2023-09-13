package BankMangementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {
    JButton withdraw , back;
    JTextField amount;
    String pinnumber;
    Withdrawl(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/ATMScreen.png"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Enter the Amount you want to Withdraw");
        text.setFont(new Font("System",Font.BOLD,16));
        text.setForeground(Color.WHITE);
        text.setBounds(180,130,400,20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(160,180,320,25);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(345,270,150,30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(345,310,150,30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==withdraw){
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")){
                JOptionPane.showMessageDialog(null,"Plese Enter The Amount You Want To Withdraw");

            }else {
                try {
                    classforName("Com.mysql.cj.jdbc.Driver");
                    String url ="jdbc:mysql://localhost:3306/bams";
                    String Uname = "shivam";
                    String Upass = "123";
                    Connection con = DriverManager.getConnection(url,Uname,Upass);
                    String query = "insert into bank values('"+pinnumber+"','"+date+"','Withdrawl','"+number+"')";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs "+number+"  Withdraw Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }catch (Exception e){
                    System.out.println(e);
                }
            }

        } else if (ae.getSource()==back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        }
    }
    public static void main(String[] args) {

        new Withdrawl("");
    }
    private static void classforName(String args){

    }
}

