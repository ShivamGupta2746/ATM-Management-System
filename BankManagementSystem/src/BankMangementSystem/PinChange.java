package BankMangementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PinChange extends JFrame implements ActionListener {
    JPasswordField pin,repin;
    JButton change,back;
    String pinnumber;
    PinChange(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/ATMScreen.png"));
        Image i2= i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setBounds(230,130,600,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        JLabel pintext = new JLabel("New PIN:");
        pintext.setBounds(140,165,100,25);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System",Font.BOLD,16));
        image.add(pintext);

        pin =new JPasswordField();
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        pin.setBounds(290,165,200,25);
        image.add(pin);

        JLabel repintext = new JLabel("Re-Enter New Pin:");
        repintext.setBounds(140,200,180,25);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System",Font.BOLD,16));
        image.add(repintext);

        repin =new JPasswordField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(290,200,200,25);
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(355,280,150,30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(355,320,150,30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==change){

            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)){
                    JOptionPane.showMessageDialog(null,"Entered pin does not match");
                    return;
                }

                if (npin.equals("")){
                    JOptionPane.showMessageDialog(null,"Please Enter PIN");
                    return;
                }
                if (rpin.equals("")){
                    JOptionPane.showMessageDialog(null,"Please Enter Pin");
                    return;
                }

                classforName("Com.mysql.cj.jdbc.Driver");
                String url ="jdbc:mysql://localhost:3306/bams";
                String Uname = "shivam";
                String Upass = "123";
                Connection con = DriverManager.getConnection(url,Uname,Upass);

                String query1 = "update bank set pin = '"+rpin+"' where pin ='"+pinnumber+"'";
                String query2 = "update login set pin = '"+rpin+"' where pin ='"+pinnumber+"'";
                String query3 = "update signupthree set pin = '"+rpin+"' where pin ='"+pinnumber+"'";

                Statement stmt = con.createStatement();
                stmt.executeUpdate(query1);
                stmt.executeUpdate(query2);
                stmt.executeUpdate(query3);

                JOptionPane.showMessageDialog(null,"Pin Changed Successfull");
                setVisible(false);
                new Transactions(rpin).setVisible(true);

            }catch (Exception e){
                System.out.println(e);
            }
        }else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }


    }
    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
    private static void classforName(String args){

    }
}
