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
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton button100, button500, button1000, button2000, button5000, button10000, backButton;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 900);
        add(imageLabel);

        JLabel text = new JLabel("Select Withdrawal Amount: ");
        text.setBounds(200, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        imageLabel.add(text);

        button100 = new JButton("100");
        button100.setBounds(170, 415, 150, 30);
        button100.addActionListener(this);
        imageLabel.add(button100);

        button500 = new JButton("500");
        button500.setBounds(355, 415, 150, 30);
        button500.addActionListener(this);
        imageLabel.add(button500);

        button1000 = new JButton("1000");
        button1000.setBounds(170, 450, 150, 30);
        button1000.addActionListener(this);
        imageLabel.add(button1000);

        button2000 = new JButton("2000");
        button2000.setBounds(355, 450, 150, 30);
        button2000.addActionListener(this);
        imageLabel.add(button2000);

        button5000 = new JButton("5000");
        button5000.setBounds(170, 485, 150, 30);
        button5000.addActionListener(this);
        imageLabel.add(button5000);

        button10000 = new JButton("10000");
        button10000.setBounds(355, 485, 150, 30);
        button10000.addActionListener(this);
        imageLabel.add(button10000);

        backButton = new JButton("Back");
        backButton.setBounds(355, 520, 150, 30);
        backButton.addActionListener(this);
        imageLabel.add(backButton);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText();
            int withdrawalAmount = Integer.parseInt(amount);

            Conn c = new Conn();

            try {
                // Update the balanceQuery
                String balanceQuery = "SELECT * FROM bank WHERE pin = ?";
                PreparedStatement balanceStmt = c.c.prepareStatement(balanceQuery);
                balanceStmt.setString(1, pinnumber);
                ResultSet rs = balanceStmt.executeQuery();

                int balance = 0;

                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                if (balance < withdrawalAmount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                Date date = new Date();
                String insertQuery = "INSERT INTO bank VALUES(?, ?, ?, ?)";
                PreparedStatement insertStmt = c.c.prepareStatement(insertQuery);
                insertStmt.setString(1, pinnumber);
                insertStmt.setString(2, date.toString());
                insertStmt.setString(3, "withdrawal");
                insertStmt.setString(4, amount);

                insertStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");

                setVisible(false);
                new Transaction(pinnumber).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new FastCash("");
    }
}
