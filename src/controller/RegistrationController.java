/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;


import dao.RegistrationDao;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.RegistrationModel;
import view.Registration;
/**
 *
 * @author A plus
 */
public class RegistrationController {

    private final RegistrationDao userDao = new RegistrationDao();
    private final Registration userView;

    public RegistrationController(Registration userView) {
        this.userView = userView;
        userView.addUserListner(new RegisterListener());
    }

    public void open() {
        userView.setVisible(true);
    }

    public void close() {
        userView.dispose();
    }

    // ❌ NOT static
    private class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String username = userView.getUsernameField().getText();
                String email = userView.getEmailField().getText();
                String contact = userView.getContactField().getText();
                String password = userView.getPasswordField().getText();
                String confirmPassword = userView.getConfirmPasswordField().getText();

                // Password check
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(userView, "Passwords do not match");
                    return;
                }

                RegistrationModel user = new RegistrationModel(username, email, password, contact);

                boolean exists = userDao.check(user);
                if (exists) {
                    JOptionPane.showMessageDialog(userView, "User already exists");
                } else {
                    userDao.Register(user);
                    JOptionPane.showMessageDialog(userView, "Registration successful");
                    userView.clearForm();
                }

            } catch (HeadlessException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(userView, "Error: " + ex.getMessage());
            }
        }
    }
}