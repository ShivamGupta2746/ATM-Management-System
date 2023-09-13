package BankMangementSystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

import com.toedter.calendar.JDateChooser;

public class SignupTwo extends JFrame implements ActionListener{
//    long random;
    JTextField pan,aadhaar;
    JButton next;
    JRadioButton syes,sno,eyes , eno;
    JComboBox religion , category,income, occupation, education;
    String formno;
    SignupTwo(String formno){
        this.formno =formno;
        setLayout(null);
       setTitle("New Account Application Form-Page 2");


        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        additionalDetails.setBounds(290,80,400,30);
        add(additionalDetails);

        JLabel name = new JLabel("Religion:");
        name.setFont(new Font("Raleway",Font.BOLD,20));
        name.setBounds(100,140,100,30);
        add(name);

        String valReligion [] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox<>(valReligion);
        religion.setBounds(300,140,400,30);
        religion.setBackground(Color.WHITE);
        add(religion);


        JLabel fname = new JLabel(" Category:");
        fname.setFont(new Font("Raleway",Font.BOLD,20));
        fname.setBounds(100,190,200,30);
        add(fname);

        String valCategory[] = {"General", "OBC", "SC", "ST", "Other"};
        category=new JComboBox<>(valCategory);
        category.setBounds(300,190,400,30);
        category.setBackground(Color.WHITE);
        add(category);


        JLabel dob = new JLabel("Income:");
        dob.setFont(new Font("Raleway",Font.BOLD,20));
        dob.setBounds(100,240,200,30);
        add(dob);

        String incomeCategory[] = {"Null", "< 1.5L", "< 2.5L", "< 5.0L", "Up To 10L"};
        income=new JComboBox<>(incomeCategory);
        income.setBounds(300,240,400,30);
        income.setBackground(Color.WHITE);
        add(income);


        JLabel gender = new JLabel("Education");
        gender.setFont(new Font("Raleway",Font.BOLD,20));
        gender.setBounds(100,290,200,30);
        add(gender);


        JLabel email = new JLabel("Qualification:");
        email.setFont(new Font("Raleway",Font.BOLD,20));
        email.setBounds(100,315,200,30);
        add(email);

        String educationalValues[] = {"Non-Graduate", "Post-Graduation", "Under-Graduate", "Doctrate", "Other"};
        education=new JComboBox<>(educationalValues);
        education.setBounds(300,315,400,30);
        education.setBackground(Color.WHITE);
        add(education);


        JLabel marital = new JLabel("Occupation:");
        marital.setFont(new Font("Raleway",Font.BOLD,20));
        marital.setBounds(100,390,200,30);
        add(marital);

        String occupationValues[] = {"Salaried", "Self-Employee", "Bussiness", "Student", "Retired","Others"};
        occupation=new JComboBox<>(occupationValues);
        occupation.setBounds(300,390,400,30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel address = new JLabel("PAN No:");
        address.setFont(new Font("Raleway",Font.BOLD,20));
        address.setBounds(100,440,200,30);
        add(address);

        pan = new JTextField();
        pan.setFont(new Font("Raleway",Font.BOLD,14));
        pan.setBounds(300,440,400,30);
        add(pan);

        JLabel city = new JLabel("Aadhaar No:");
        city.setFont(new Font("Raleway",Font.BOLD,20));
        city.setBounds(100,490,200,30);
        add(city);

        aadhaar = new JTextField();
        aadhaar.setFont(new Font("Raleway",Font.BOLD,14));
        aadhaar.setBounds(300,490,400,30);
        add(aadhaar);

        JLabel state = new JLabel("Senior Citizen:");
        state.setFont(new Font("Raleway",Font.BOLD,20));
        state.setBounds(100,540,200,30);
        add(state);

        syes=new JRadioButton("Yes");
        syes.setBounds(300,540,100,30);
        syes.setBackground(Color.WHITE);
        add(syes);

        sno=new JRadioButton("No");
        sno.setBounds(450,540,100,30);
        sno.setBackground(Color.WHITE);
        add(sno);

        ButtonGroup maritalgoup = new ButtonGroup();
        maritalgoup.add(syes);
        maritalgoup.add(sno);


        JLabel pincode = new JLabel("Existing Account:");
        pincode.setFont(new Font("Raleway",Font.BOLD,20));
        pincode.setBounds(100,590,200,30);
        add(pincode);

        eyes=new JRadioButton("Yes");
        eyes.setBounds(300,590,100,30);
        eyes.setBackground(Color.WHITE);
        add(eyes);

        eno=new JRadioButton("No");
        eno.setBounds(450,590,100,30);
        eno.setBackground(Color.WHITE);
        add(eno);

        ButtonGroup emaritalgoup = new ButtonGroup();
        emaritalgoup.add(eyes);
        emaritalgoup.add(eno);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);


        getContentPane().setBackground(Color.WHITE);
        setSize(850,800);
        setLocation(350,10);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) {
//        String formno = "" + random;
        String sreligion = (String) religion.getSelectedItem();
        String scategory =  (String)category.getSelectedItem();
        String sincome =(String)income.getSelectedItem();
        String seducation = (String)education.getSelectedItem();
        String soccupation = (String)occupation.getSelectedItem();
        String seniorcitizen = null;
        if (syes.isSelected()) {
            seniorcitizen = "YES";
        } else if (sno.isSelected()) {
            seniorcitizen = "NO";
        }


        String existingaccount = null;
        if (eyes.isSelected()) {
            existingaccount = "YES";
        } else if (eno.isSelected()) {
            existingaccount = "NO";
        }
        String span = pan.getText();
        String saadhaar = aadhaar.getText();

        try {
            classforName("Com.mysql.cj.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/bams";
            String Uname = "shivam";
            String Upass = "123";
            Connection con = DriverManager.getConnection(url,Uname,Upass);
            String query = "insert into signupTwo values('"+formno+"','"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"','"+soccupation+"','"+span+"','"+saadhaar+"','"+seniorcitizen+"','"+existingaccount+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);

            setVisible(false);
            new SignupThree(formno).setVisible(true);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("");
    }
    private static void classforName(String args){

    }
    }