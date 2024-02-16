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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin, repin;
    JButton change, back;
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel pintext = new JLabel("Change Your PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(250, 280, 500, 35);
        image.add(pintext);

        JLabel repintext = new JLabel("New PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 320, 180, 25);
        image.add(repintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330, 320, 180, 25);
        image.add(pin);

        JLabel rerepintext = new JLabel("Re-Enter Your PIN");
        rerepintext.setForeground(Color.WHITE);
        rerepintext.setFont(new Font("System", Font.BOLD, 16));
        rerepintext.setBounds(165, 360, 180, 25);
        image.add(rerepintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(330, 360, 180, 25);
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
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
        if (ae.getSource() == change) {
            try {
                String newPin = new String(pin.getPassword());  // Use getPassword() for JPasswordField
                String reenteredPin = new String(repin.getPassword());  // Use getPassword() for JPasswordField

                // Validation
                if (newPin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter PIN");
                    return;
                }

                if (reenteredPin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }

                if (!newPin.equals(reenteredPin)) {
                    JOptionPane.showMessageDialog(null, "PINs do not match. Please try again.");
                    return;
                }

                // Update the PIN in the database
                Conn conn = new Conn();
                String updateBankQuery = "Update bank set pin = '" + reenteredPin + "' where pin='" + pinnumber + "'";
                String updateLoginQuery = "Update login set pin = '" + reenteredPin + "' where pin='" + pinnumber + "'";
                String updateSignupQuery = "Update signupthree set pin = '" + reenteredPin + "' where pin='" + pinnumber + "'";

                conn.s.executeUpdate(updateBankQuery);
                conn.s.executeUpdate(updateLoginQuery);
                conn.s.executeUpdate(updateSignupQuery);

                JOptionPane.showMessageDialog(null, "PIN changed successfully!");

                // Navigate to the Transaction screen
                setVisible(false);
                new Transaction(pinnumber).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            // Handle BACK button click
            setVisible(false);
            new Transaction("").setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
