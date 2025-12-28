package group1_uunchai;

import Database.MySqlConnection;
import view.Login;
import controller.LoginController;
import javax.swing.*;

public class Group1_Uunchai {
    public static void main(String[] args) {
        System.out.println("🚀 Starting Flight Booking System");
        System.out.println("==================================");
        
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting look and feel: " + e.getMessage());
        }
        
        // Test database connection first
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Testing database connection...");
                MySqlConnection db = new MySqlConnection();
                java.sql.Connection conn = db.openConnection();
                
                if (conn == null) {
                    JOptionPane.showMessageDialog(null,
                        "❌ DATABASE CONNECTION FAILED!\n\n" +
                        "Please ensure:\n" +
                        "1. MySQL is running\n" +
                        "2. Database 'project' exists\n" +
                        "3. Check connection settings in MySqlConnection.java\n\n" +
                        "Error details in console.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                db.closeConnection(conn);
                System.out.println("✅ Database connection OK");
                
                // Start application
                Login login = new Login();
                new LoginController(login);
                login.setVisible(true);
                
                System.out.println("✅ Application started successfully!");
                System.out.println("\n📋 Test Credentials:");
                System.out.println("Email: ashish123@gmail.com");
                System.out.println("Password: ashish@123");
                System.out.println("\nWaiting for user login...");
                
            } catch (Exception e) {
                System.err.println("Critical error: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                    "Failed to start application: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}