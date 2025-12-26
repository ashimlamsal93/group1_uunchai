/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import dao.UserDao;
import javax.swing.JOptionPane;

public class ResetPasswordController {
    private UserDao userDao;
    
    public ResetPasswordController() {
        this.userDao = new UserDao();
    }
    
    // Step 1: Get security question for email
    public String getSecurityQuestion(String email) {
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your email!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        if (!userDao.emailExists(email)) {
            JOptionPane.showMessageDialog(null, "Email not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        return userDao.getSecurityQuestion(email);
    }
    
    // Step 2: Verify security answer and reset password
    public boolean handlePasswordReset(String email, String securityAnswer, String newPassword, String confirmPassword) {
        // Validate inputs
        if (email.isEmpty() || securityAnswer.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Verify security answer
        if (!userDao.verifySecurityAnswer(email, securityAnswer)) {
            JOptionPane.showMessageDialog(null, "Incorrect security answer!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Update password
        if (userDao.updatePassword(email, newPassword)) {
            JOptionPane.showMessageDialog(null, "Password reset successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password reset failed!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
