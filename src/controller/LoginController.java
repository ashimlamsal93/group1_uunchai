package controller;

import dao.UserDao;
import model.User;
import view.UserDashboard;
import view.AdminDashboard;
import view.Login;
import javax.swing.JOptionPane;

public class LoginController {
    private UserDao userDao;
    
    public LoginController() {
        this.userDao = new UserDao();
        System.out.println("LoginController created");
    }
    
    public void handleLogin(String email, String password, javax.swing.JFrame currentFrame) {
        System.out.println("=== LOGIN ATTEMPT ===");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        
        // Validate inputs
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("ERROR: Empty fields");
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Attempt login
        System.out.println("Attempting database login...");
        User user = userDao.loginUser(email, password);
        
        if (user != null) {
            System.out.println("Login successful! User: " + user.getName() + ", Role: " + user.getRole());
            JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + user.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Close current window
            System.out.println("Closing login window...");
            currentFrame.setVisible(false);
            currentFrame.dispose();
            
            // Navigate based on role
            try {
                if ("ADMIN".equals(user.getRole())) {
                    System.out.println("Creating AdminDashboard...");
                    AdminDashboard dashboard = new AdminDashboard();
                    dashboard.setVisible(true);
                    currentFrame.dispose();
                    System.out.println("AdminDashboard created and shown!");
                } else {
                    System.out.println("Creating UserDashboard...");
                    UserDashboard dashboard = new UserDashboard();
                    dashboard.setVisible(true);
                    currentFrame.dispose();
                    System.out.println("UserDashboard created and shown!");
                }
            } catch (Exception e) {
                System.out.println("ERROR creating dashboard:");
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error opening dashboard: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            System.out.println("Login failed: Invalid credentials");
            JOptionPane.showMessageDialog(null, "Invalid email or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("=== LOGIN ATTEMPT END ===\n");
    }
}