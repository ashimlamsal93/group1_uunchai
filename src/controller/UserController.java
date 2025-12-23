/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.UserModel;
import view.Registration;

public class UserController {

    private final UserDao userDao = new UserDao();
    private final Registration userView;

    public UserController(Registration userView) {
        this.userView = userView;
        userView.addUserListner(new RegisterListener());
    }

    public void open() {
        userView.setVisible(true);
    }

    private class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String username = userView.getUsernameField().getText();
            String email = userView.getEmailField().getText();
            String contact = userView.getContactField().getText();
            String password = userView.getPasswordField().getText();
            String confirm = userView.getConfirmPasswordField().getText();

            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(userView, "Passwords do not match");
                return;
            }

            UserModel user = new UserModel(username, email, password, contact);

            if (userDao.exists(user)) {
                JOptionPane.showMessageDialog(userView, "User already exists");
            } else {
                boolean success = userDao.register(user);
                if (success) {
                    JOptionPane.showMessageDialog(userView, "Registration successful");
                    userView.clearForm();
                } else {
                    JOptionPane.showMessageDialog(userView, "Registration failed");
                }
            }
        }
    }
}