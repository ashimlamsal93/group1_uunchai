/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.ResetPasswordDao;

public class ResetPasswordController {

    private final ResetPasswordDao userDAO = new ResetPasswordDao();

    public String resetPassword(String email, String newPass, String confirmPass) {

        // Check empty fields
        if (email.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            return "Please fill all fields.";
        }

        // Check passwords match
        if (!newPass.equals(confirmPass)) {
            return "Passwords do not match!";
        }

        // Check email exists
        if (!userDAO.emailExists(email)) {
            return "Email not found!";
        }

        // Update password
        boolean updated = userDAO.updatePassword(email, newPass);

        if (updated) {
            return "Password updated successfully.";
        } else {
            return "Error updating password.";
        }
    }
}