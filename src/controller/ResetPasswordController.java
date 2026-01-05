/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao;
import javax.swing.JOptionPane;

public class ResetPasswordController {
    private final UserDao userDao;

    public ResetPasswordController() {
        this.userDao = new UserDao();
    }

    // Step 1: Get security question for email
    public String getSecurityQuestion(String email) {
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your email!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        email = email.trim();

        if (!userDao.emailExists(email)) {
            JOptionPane.showMessageDialog(null, "Email not found in our system!", "Not Found", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String question = userDao.getSecurityQuestion(email);
        if (question == null || question.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Security question not set for this account.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return question;
    }

    // Step 2: Verify security answer + question and reset password
    public boolean handlePasswordReset(String email, String selectedQuestion, String securityAnswer, String newPassword, String confirmPassword) {
        // Validate all inputs
        if (email == null || email.trim().isEmpty() ||
            selectedQuestion == null || selectedQuestion.trim().isEmpty() ||
            securityAnswer == null || securityAnswer.trim().isEmpty() ||
            newPassword == null || confirmPassword == null) {
            
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        email = email.trim();
        selectedQuestion = selectedQuestion.trim();
        securityAnswer = securityAnswer.trim();

        // Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check password strength (optional but recommended)
        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long!", "Weak Password", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Verify BOTH question and answer
        if (!userDao.verifySecurityAnswer(email, selectedQuestion, securityAnswer)) {
            JOptionPane.showMessageDialog(null, "Incorrect security question or answer!", "Verification Failed", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Update password
        if (userDao.updatePassword(email, newPassword)) {
            JOptionPane.showMessageDialog(null, 
                "Password reset successful!\nYou can now login with your new password.", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update password. Try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}