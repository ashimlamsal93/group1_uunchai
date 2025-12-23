package controller;

import dao.LoginDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import model.UserModel;
import view.Login;

public class LoginController {
    private final LoginDao loginDao;
    private final Login loginView;
    private final char defaultEchoChar;

    public LoginController(Login loginView) {
        this.loginView = loginView;
        this.loginDao = new LoginDao();
        this.defaultEchoChar = loginView.getPasswordField().getEchoChar();
        initEvents();
        System.out.println("✓ LoginController initialized");
    }

    public void open() {
        this.loginView.setVisible(true);
    }

    public void close() {
        this.loginView.dispose();
    }

    private void initEvents() {
        // Login button listener
        loginView.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        
        // Register label click listener
        loginView.addRegisterListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openRegistration();
            }
        });
        
        // Forgot Password label click listener
        loginView.addForgotPasswordListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openForgotPassword();
            }
        });
        
        // Show password checkbox listener
        loginView.addShowPasswordListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });
    }

    private void togglePasswordVisibility() {
        if (loginView.getShowPasswordCheckbox().isSelected()) {
            loginView.getPasswordField().setEchoChar((char) 0);
            System.out.println("✓ Password visibility: ON");
        } else {
            loginView.getPasswordField().setEchoChar(defaultEchoChar);
            System.out.println("✓ Password visibility: OFF");
        }
    }

    private void performLogin() {
        try {
            String email = loginView.getEmailField().getText().trim();
            String password = new String(loginView.getPasswordField().getPassword()).trim();
            
            System.out.println("Login attempt - Email: " + email);
            
            // Validation
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginView, 
                    "Please enter both email and password!", 
                    "Validation Error", 
                    JOptionPane.WARNING_MESSAGE);
                System.out.println("✗ Validation failed: Empty fields");
                return;
            }
            
            // Email format validation
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(loginView, 
                    "Please enter a valid email address!\nExample: user@example.com", 
                    "Invalid Email", 
                    JOptionPane.WARNING_MESSAGE);
                System.out.println("✗ Validation failed: Invalid email format");
                return;
            }
            
            // Password length validation
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(loginView, 
                    "Password must be at least 6 characters long!", 
                    "Weak Password", 
                    JOptionPane.WARNING_MESSAGE);
                System.out.println("✗ Validation failed: Password too short");
                return;
            }

            // Authenticate user
            UserModel user = loginDao.authenticateUser(email, password);
            
            if (user != null) {
                // Login successful
                System.out.println("✓ Login successful for user: " + user.getEmail() + " (ID: " + user.getUserId() + ")");
                
                JOptionPane.showMessageDialog(loginView, 
                    "✅ Login Successful!\n\nWelcome back, " + user.getEmail(), 
                    "Welcome!", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Clear fields after successful login
                loginView.getEmailField().setText("");
                loginView.getPasswordField().setText("");
                loginView.getShowPasswordCheckbox().setSelected(false);
                
                // Here you would typically open the main application window
                // For example:
                // loginView.dispose();
                // MainWindow mainWindow = new MainWindow();
                // mainWindow.setUser(user);
                // mainWindow.setVisible(true);
                
            } else {
                // Login failed
                System.out.println("✗ Login failed - Invalid credentials for: " + email);
                
                JOptionPane.showMessageDialog(loginView, 
                    "❌ Login Failed!\n\nInvalid email or password.\n\nPlease check your credentials and try again.", 
                    "Authentication Failed", 
                    JOptionPane.ERROR_MESSAGE);
                
                // Clear password field for retry
                loginView.getPasswordField().setText("");
                loginView.getPasswordField().requestFocus();
            }
            
        } catch (Exception ex) {
            System.err.println("✗ Error during login: " + ex.getMessage());
            ex.printStackTrace();
            
            JOptionPane.showMessageDialog(loginView, 
                "⚠️ System Error!\n\nAn unexpected error occurred:\n" + ex.getMessage() + "\n\nPlease try again later.", 
                "System Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegistration() {
        System.out.println("Registration option clicked");
        
        int choice = JOptionPane.showConfirmDialog(loginView, 
            "Would you like to register a new account?", 
            "New User", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(loginView, 
                "Registration form would open here.\n\nThis would open a new registration window.", 
                "Registration", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void openForgotPassword() {
        System.out.println("Forgot password clicked");
        
        // Show input dialog for email
        String email = JOptionPane.showInputDialog(loginView,
            "Enter your email address to reset password:",
            "Forgot Password",
            JOptionPane.QUESTION_MESSAGE);
        
        if (email != null && !email.trim().isEmpty()) {
            email = email.trim();
            
            // Validate email format
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(loginView,
                    "Please enter a valid email address!",
                    "Invalid Email",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Check if email exists in database
            boolean emailExists = loginDao.checkEmailExists(email);
            
            if (emailExists) {
                // Email found, ask for new password
                String newPassword = JOptionPane.showInputDialog(loginView,
                    "Enter new password (minimum 6 characters):",
                    "Reset Password",
                    JOptionPane.QUESTION_MESSAGE);
                
                if (newPassword != null && !newPassword.trim().isEmpty()) {
                    newPassword = newPassword.trim();
                    
                    // Validate password length
                    if (newPassword.length() < 6) {
                        JOptionPane.showMessageDialog(loginView,
                            "Password must be at least 6 characters long!",
                            "Weak Password",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    // Confirm password
                    String confirmPassword = JOptionPane.showInputDialog(loginView,
                        "Confirm new password:",
                        "Reset Password",
                        JOptionPane.QUESTION_MESSAGE);
                    
                    if (newPassword.equals(confirmPassword)) {
                        // Update password in database
                        boolean success = loginDao.resetPassword(email, newPassword);
                        
                        if (success) {
                            JOptionPane.showMessageDialog(loginView,
                                "✅ Password reset successful!\n\nYou can now login with your new password.",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("✓ Password reset for email: " + email);
                        } else {
                            JOptionPane.showMessageDialog(loginView,
                                "❌ Failed to reset password. Please try again.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(loginView,
                            "❌ Passwords do not match!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(loginView,
                    "❌ Email not found in our system.\n\nPlease check the email or register for a new account.",
                    "Email Not Found",
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private boolean isValidEmail(String email) {
        // Simple email validation
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}