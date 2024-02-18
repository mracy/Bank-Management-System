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

public class Transaction extends JFrame implements ActionListener {

    JButton deposit, withdrawal, ministatement, pinchange, fastcash, balanceenquiry, exit;
    String pinnumber;

    Transaction(String pinnumber) {

        this.pinnumber = pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 900);
        add(imageLabel);

        JLabel text = new JLabel("Please select your Transaction");
        text.setBounds(200, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        imageLabel.add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        imageLabel.add(deposit);

        withdrawal = new JButton("Cash Withdrawal");
        withdrawal.setBounds(355, 415, 150, 30);
        withdrawal.addActionListener(this);
        imageLabel.add(withdrawal);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(170, 450, 150, 30);
        fastcash.addActionListener(this);
        imageLabel.add(fastcash);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(355, 450, 150, 30);
        ministatement.addActionListener(this);
        imageLabel.add(ministatement);

        pinchange = new JButton("Change PIN");
        pinchange.setBounds(170, 485, 150, 30);
        pinchange.addActionListener(this);
        imageLabel.add(pinchange);

        balanceenquiry = new JButton("Balance Enquiry");
        balanceenquiry.setBounds(355, 485, 150, 30);
        balanceenquiry.addActionListener(this);
        imageLabel.add(balanceenquiry);

        exit = new JButton("Exit");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        imageLabel.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        } else if (ae.getSource() == withdrawal) {
            setVisible(false);
            new Withdrawl(pinnumber).setVisible(true);  // Corrected the class name
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);  // Corrected the class name
        } else if (ae.getSource() == pinchange) {
            setVisible(false);
            new PinChange(pinnumber).setVisible(true);
        } else if (ae.getSource() == balanceenquiry) {
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        } else if (ae.getSource() == ministatement) {

            new MiniStatements(pinnumber).setVisible(true);
        } 
    }

    public static void main(String args[]) {
        new Transaction("");
    }
}