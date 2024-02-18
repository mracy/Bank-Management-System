package bank.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        // Corrected image path
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        image.add(back);

        int balance = 0;

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM bank WHERE pin = ?";
            PreparedStatement pstmt = c.c.prepareStatement(query);
            pstmt.setString(1, pinnumber);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel text = new JLabel("Your Current Account Balance is Rs: " + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 400, 30);
        image.add(text);

        // Add ActionListener to the "Back" button
        back.addActionListener(this);

        setSize(900, 900);
        setLocation(300, 0);

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
