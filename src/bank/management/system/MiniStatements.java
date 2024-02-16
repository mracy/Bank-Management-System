package bank.management.system;

import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniStatements extends JFrame {

    JLabel card, bank, mini, balance;  

    MiniStatements(String pinnumber) {
        setTitle("Mini Statement");

        setLayout(null);

        bank = new JLabel("Nepal Bank LTD.");
        bank.setBounds(150, 20, 150, 20);
        add(bank);

        card = new JLabel();  // Initialize 'card' label
        card.setBounds(45, 80, 300, 20);
        add(card);

        mini = new JLabel(); // Initialize 'mini' label
        mini.setBounds(20, 140, 400, 255);
        add(mini);

        balance = new JLabel(); // Initialize 'balance' label
        balance.setBounds(45, 450, 300, 20);
        add(balance);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '" + pinnumber + "'");
            while (rs.next()) {
                // Display only the first four characters of the card number
                card.setText("Card number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Conn conn = new Conn();
            int capital = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>" );
                if (rs.getString("type").equals("Deposit")) {
                    capital += Integer.parseInt(rs.getString("amount"));
                } else {
                    capital -= Integer.parseInt(rs.getString("amount"));
                }
            }
            
            balance.setText("Your Current Account Balance is Rs: " + capital);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        new MiniStatements("");
    }
}
