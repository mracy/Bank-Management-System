package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Deposit extends JFrame implements ActionListener {

    JTextField amountField;
    JButton deposit, back;  
    String pinnumber;

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit:");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        amountField = new JTextField();
        amountField.setFont(new Font("Raleway", Font.BOLD, 22));
        amountField.setBounds(170, 350, 320, 25);
        image.add(amountField);

        deposit = new JButton("Deposit");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this); 
        
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);  
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String number = amountField.getText();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
            } else {
                Date date = new Date();
                Conn conn = new Conn();
                String query = "INSERT INTO bank VALUES(?, ?, ?, ?)";
                try {
                    PreparedStatement pstmt = conn.c.prepareStatement(query);
                    pstmt.setString(1, pinnumber);
                    pstmt.setString(2, date.toString());
                    pstmt.setString(3, "Deposit");
                    pstmt.setString(4, number);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Rs" + number + " Deposited Successfully");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
