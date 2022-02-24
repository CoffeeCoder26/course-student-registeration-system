package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        try {

            // loading MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // get a Connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Registration", "root", "");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Connection Failed .....!");
        }

        return connection;
    }

//    public static void main(String[] args) {
//
//        getConnection();
//    }
}