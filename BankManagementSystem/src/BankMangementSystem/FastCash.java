package BankMangementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton deposit , withdrawl, ministatement, pinchange , fastcash , balanceenquiry , exit;
    String pinnumber;
    FastCash(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/ATMScreen.png"));
        Image i2= i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(200,130,600,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        deposit = new JButton("Rs 100");
        deposit.setBounds(140,170,150,30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Rs 500");
        withdrawl.setBounds(325,170,150,30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("Rs 1000");
        fastcash.setBounds(140,215,150,30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Rs 2000");
        ministatement.setBounds(325,215,150,30);
        ministatement.addActionListener(this);
        image.add(ministatement);


        pinchange = new JButton("Rs 5000");
        pinchange.setBounds(140,260,150,30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("Rs 10000");
        balanceenquiry.setBounds(325,260,150,30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("Back ");
        exit.setBounds(325,310,150,30);
        exit.addActionListener(this);
        image.add(exit);



        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==exit){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            String amount=((JButton)ae.getSource()).getText().substring(3);
            try {

                classforName("Com.mysql.cj.jdbc.Driver");
                String url ="jdbc:mysql://localhost:3306/bams";
                String Uname = "shivam";
                String Upass = "123";
                Connection con = DriverManager.getConnection(url,Uname,Upass);
                String query = "select * from bank where pin ='"+pinnumber+"' ";
                Statement stmt = con.createStatement();
                ResultSet rs =stmt.executeQuery(query);

                int balance =0;
                while (rs.next()){
                    if (rs.getString("type").equals("Deposit")){
                        balance +=Integer.parseInt(rs.getString("amount"));
                    }else {
                        balance -=Integer.parseInt(rs.getString("amount"));
                    }
                }

                if (ae.getSource()!=exit && balance< Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }

                Date date = new Date();
                String query1 = "insert into bank values('"+pinnumber+"','"+date+"','Withdrawl','"+amount+"')";
                stmt.executeUpdate(query1);
                JOptionPane.showMessageDialog(null,"Rs  "+amount+"  Debited Successfull");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);


            }catch (Exception e){
                System.out.println(e);
            }
        }

    }

    public static void main(String[] args) {
        new FastCash("");
    }
    private static void classforName(String args){

    }
}

