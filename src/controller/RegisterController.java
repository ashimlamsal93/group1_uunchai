/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.UserDao;
import model.User;
import javax.swing.JOptionPane;

public class RegisterController {
    private UserDao userDao;
    
    public RegisterController() {
        this.userDao = new UserDao();
    }
    
    public boolean handleRegistration(String username, String email, String password, String phone, 
                                     String securityQuestion, String securityAnswer) {
        // Validate inputs
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || 
            securityQuestion.isEmpty() || securityAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Check if email already exists
        if (userDao.emailExists(email)) {
            JOptionPane.showMessageDialog(null, "Email already registered!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Create new user
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRole("USER");
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer);
        
        // Register user
        if (userDao.registerUser(user)) {
            JOptionPane.showMessageDialog(null, "Registration Successful! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Registration Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
