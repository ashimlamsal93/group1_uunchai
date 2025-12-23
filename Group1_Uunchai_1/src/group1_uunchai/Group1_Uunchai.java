package group1_uunchai;

import javax.swing.UIManager;
import view.Login;

public class Group1_Uunchai {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("User Login System - Starting");
        System.out.println("=========================================");
        
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("✓ System look and feel set");
        } catch (Exception e) {
            System.err.println("✗ Error setting look and feel: " + e.getMessage());
        }
        
        // Create and display the login form
        java.awt.EventQueue.invokeLater(() -> {
            try {
                Login loginView = new Login();
                loginView.setVisible(true);
                System.out.println("✓ Login window displayed");
                System.out.println("✓ Application ready");
                System.out.println("=========================================");
                System.out.println("\n📋 Test Credentials:");
                System.out.println("• ashish123@gmail.com / ashish@123");
                System.out.println("• abhishek123@gmail.com / abhishek@123");
                System.out.println("• akash123@gmail.com / akash@123");
                System.out.println("• laxmi123@gmail.com / laxmi@123");
                System.out.println("• lucky123@gmail.com / lucky@123");
                System.out.println("=========================================");
            } catch (Exception e) {
                System.err.println("✗ Error starting application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}