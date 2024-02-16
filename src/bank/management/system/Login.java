package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    public Login() {
        setTitle("AUTOMATED TELLER MACHINE");

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Load the image using ImageIcon
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));

        // Scale the image
        Image I2 = I1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        // Create a new ImageIcon with the scaled image
        ImageIcon scaledIcon = new ImageIcon(I2);

        // Create a JLabel to display the scaled image
        JLabel label = new JLabel(scaledIcon);

        // Set the position and size of the label
        label.setBounds(70, 10, 100, 100);

        // Add the label to the JFrame
        add(label);

        JLabel text = new JLabel("Welcome To ATM");

        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);

        // Add the text label to the JFrame
        add(text);

        JLabel cardno = new JLabel("Card No.");

        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);

        // Add the text label to the JFrame
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN: ");

        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);

        // Add the text label to the JFrame
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.WHITE);
        login.setForeground(Color.BLACK);

        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.WHITE);
        clear.setForeground(Color.BLACK);

        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.WHITE);
        signup.setForeground(Color.BLACK);

        signup.addActionListener(this);
        add(signup);

        setSize(800, 480);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '" + cardnumber + "' and pin = '" + pinnumber + "'";

            try {
                ResultSet rs = conn.s.executeQuery(query);
                // Process the ResultSet if needed
                if (rs.next()) {
                    setVisible(false);
                    new Transaction(pinnumber).setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
