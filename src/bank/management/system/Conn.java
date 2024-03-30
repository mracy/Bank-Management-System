package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conn {

    Connection c;
    PreparedStatement pstmt;

    public Conn() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "your_username", "your_password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // You may want to create a method to get the PreparedStatement when needed
    public PreparedStatement getPreparedStatement(String query) {
        try {
            // Create a prepared statement with the provided query
            pstmt = c.prepareStatement(query);
            return pstmt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
