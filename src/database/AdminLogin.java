package view;

import dao.AdminDAO;
import model.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author DELL
 */
public class AdminLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminLogin.class.getName());
    private final AdminDAO adminDAO;
    
    /**
     * Creates new form AdminLogin
     */
    public AdminLogin() {
        initComponents();
        adminDAO = new AdminDAO();
        setupAdditionalListeners();
        centerFrame();
    }
    
    private void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        this.setLocation((screenSize.width - frameSize.width) / 2, 
                        (screenSize.height - frameSize.height) / 2);
    }
    
    private void setupAdditionalListeners() {
        // Make labels clickable
        forgotpassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returntouserlogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add Enter key listener for password field
        adminpassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });
        
        // Add click listener for forgot password
        forgotpassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleForgotPassword();
            }
        });
        
        // Add click listener for return to user login
        returntouserlogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                returnToUserLogin();
            }
        });
    }

    // ... [Keep all the existing initComponents() code as is] ...

    private void adminpasswordActionPerformed(java.awt.event.ActionEvent evt) {                                              
        performLogin();
    }                                             

    private void adminidActionPerformed(java.awt.event.ActionEvent evt) {                                        
        adminpassword.requestFocus();
    }                                       

    private void showpasswordActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (showpassword.isSelected()) {
            adminpassword.setEchoChar((char) 0); // Show password
        } else {
            adminpassword.setEchoChar('•'); // Hide password
        }
    }                                            

    private void adminloginbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        performLogin();
    }
    
    private void performLogin() {
        String adminId = adminid.getText().trim();
        String password = new String(adminpassword.getPassword());
        
        // Validation
        if (adminId.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter Admin ID", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            adminid.requestFocus();
            return;
        }
        
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter Password", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            adminpassword.requestFocus();
            return;
        }
        
        // Show loading indicator
        adminloginbutton.setEnabled(false);
        adminloginbutton.setText("Authenticating...");
        
        // Perform authentication in background thread
        SwingWorker<Admin, Void> worker = new SwingWorker<Admin, Void>() {
            @Override
            protected Admin doInBackground() throws Exception {
                return adminDAO.authenticate(adminId, password);
            }
            
            @Override
            protected void done() {
                adminloginbutton.setEnabled(true);
                adminloginbutton.setText("Login");
                
                try {
                    Admin authenticatedAdmin = get();
                    
                    if (authenticatedAdmin != null) {
                        // Login successful
                        logger.info("Admin login successful: " + adminId);
                        
                        // Store admin in session (you might want to create a Session class)
                        // Session.setCurrentAdmin(authenticatedAdmin);
                        
                        JOptionPane.showMessageDialog(AdminLogin.this, 
                            "Welcome, " + authenticatedAdmin.getFullName() + "!\n" +
                            "Role: " + authenticatedAdmin.getRole(), 
                            "Login Successful", 
                            JOptionPane.INFORMATION_MESSAGE);
                        
                        // Open admin dashboard
                        openAdminDashboard(authenticatedAdmin);
                        
                    } else {
                        // Login failed
                        logger.warning("Admin login failed for ID: " + adminId);
                        
                        JOptionPane.showMessageDialog(AdminLogin.this, 
                            "Invalid Admin ID or Password.\nPlease try again.", 
                            "Login Failed", 
                            JOptionPane.ERROR_MESSAGE);
                        
                        // Clear password field
                        adminpassword.setText("");
                        adminpassword.requestFocus();
                    }
                    
                } catch (Exception ex) {
                    logger.log(java.util.logging.Level.SEVERE, "Error during authentication", ex);
                    
                    JOptionPane.showMessageDialog(AdminLogin.this, 
                        "Database connection error. Please contact administrator.\n" +
                        "Error: " + ex.getMessage(), 
                        "System Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        
        worker.execute();
    }
    
    private void openAdminDashboard(Admin admin) {
        // Open admin dashboard window
        // AdminDashboard dashboard = new AdminDashboard(admin);
        // dashboard.setVisible(true);
        
        // For now, show a message and close login window
        JOptionPane.showMessageDialog(this, 
            "Admin Dashboard would open here.\n" +
            "Logged in as: " + admin.getFullName() + "\n" +
            "Role: " + admin.getRole(), 
            "Admin Dashboard", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Close login window
        dispose();
    }
    
    private void handleForgotPassword() {
        String adminId = JOptionPane.showInputDialog(this, 
            "Enter your Admin ID to reset password:", 
            "Forgot Password", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (adminId != null && !adminId.trim().isEmpty()) {
            Admin admin = adminDAO.getAdminById(adminId.trim());
            
            if (admin != null) {
                // Show admin details (in real app, send reset link to email)
                JOptionPane.showMessageDialog(this, 
                    "Admin Found!\n\n" +
                    "Name: " + admin.getFullName() + "\n" +
                    "Email: " + admin.getEmail() + "\n" +
                    "Phone: " + admin.getPhone() + "\n\n" +
                    "Please contact system administrator to reset your password.", 
                    "Admin Information", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Admin ID not found in system.", 
                    "Not Found", 
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private void returnToUserLogin() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Return to User Login?", 
            "Confirm", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Open user login window
            // UserLogin userLogin = new UserLogin();
            // userLogin.setVisible(true);
            
            dispose(); // Close admin login window
            JOptionPane.showMessageDialog(this, 
                "Redirecting to User Login...", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // ... [Keep the rest of the existing code] ...
}