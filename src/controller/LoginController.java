package controller;

import dao.LoginDao;
import model.UserModel;
import model.UserSession;
import view.Login;
import view.DashBoard;
import javax.swing.*;
import java.awt.event.*;

public class LoginController {
    private final Login loginView;
    private final LoginDao loginDao;
    private final char defaultEchoChar;
    
    public LoginController(Login loginView) {
        this.loginView = loginView;
        this.loginDao = new LoginDao();
        this.defaultEchoChar = loginView.getPasswordField().getEchoChar();
        initEvents();
        System.out.println("✅ LoginController initialized");
    }
    
    private void initEvents() {
        // Login button
        loginView.getLoginButton().addActionListener(e -> performLogin());
        
        // Enter key in password field
        loginView.getPasswordField().addActionListener(e -> performLogin());
        
        // Show password checkbox
        loginView.getShowPasswordCheckbox().addActionListener(e -> {
            if (loginView.getShowPasswordCheckbox().isSelected()) {
                loginView.getPasswordField().setEchoChar((char) 0);
            } else {
                loginView.getPasswordField().setEchoChar(defaultEchoChar);
            }
        });
        
        // Register label
        loginView.getRegisterLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(loginView,
                    "Registration feature coming soon!",
                    "Registration",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Forgot password
        loginView.getForgotPasswordLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = JOptionPane.showInputDialog(loginView,
                    "Enter your email to reset password:",
                    "Forgot Password",
                    JOptionPane.QUESTION_MESSAGE);
                
                if (email != null && !email.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(loginView,
                        "Password reset instructions sent to: " + email,
                        "Reset Password",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    
    private void performLogin() {
        String email = loginView.getEmailField().getText().trim();
        String password = new String(loginView.getPasswordField().getPassword()).trim();
        
        System.out.println("🔑 Login attempt for: " + email);
        
        // Validation
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginView,
                "Please enter both email and password",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(loginView,
                "Please enter a valid email address",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Authenticate user
            System.out.println("🔍 Authenticating user...");
            UserModel user = loginDao.authenticateUser(email, password);
            
            if (user != null) {
                // Store in session
                UserSession session = UserSession.getInstance();
                session.login(user.getEmail());
                
                System.out.println("✅ Login successful! Email: " + user.getEmail());
                
                // Navigate to dashboard
                SwingUtilities.invokeLater(() -> {
                    loginView.dispose(); // Close login window
                    openDashboard();
                });
                
            } else {
                System.out.println("❌ Invalid credentials");
                JOptionPane.showMessageDialog(loginView,
                    "Invalid email or password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                loginView.getPasswordField().setText("");
                loginView.getPasswordField().requestFocus();
            }
            
        } catch (Exception e) {
            System.err.println("❌ Login error: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(loginView,
                "Login failed: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openDashboard() {
        try {
            System.out.println("🚀 Opening dashboard...");
            
            DashBoard dashboard = new DashBoard();
            dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dashboard.setLocationRelativeTo(null);
            dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
            dashboard.setVisible(true);
            
            System.out.println("✅ Dashboard opened successfully!");
            
        } catch (Exception e) {
            System.err.println("❌ Failed to open dashboard: " + e.getMessage());
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null,
                "Failed to open dashboard: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            
            // Reopen login
            SwingUtilities.invokeLater(() -> {
                Login login = new Login();
                login.setVisible(true);
            });
        }
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}