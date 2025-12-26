/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import controller.LoginController;
import model.User;
import javax.swing.*;

public class Login extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private LoginController loginController;

    public Login() {
        loginController = new LoginController();
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        showpassword = new javax.swing.JRadioButton();
        password = new javax.swing.JPasswordField();
        register = new javax.swing.JLabel();
        forgotpassword = new javax.swing.JLabel();
        loginbtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(900, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        jPanel3.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user (2).png"))); // NOI18N
        jPanel3.add(jLabel2);
        jLabel2.setBounds(210, 20, 70, 70);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("User login");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(220, 110, 62, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Email:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(50, 190, 37, 16);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Password:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(30, 260, 70, 20);

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel3.add(email);
        email.setBounds(110, 180, 281, 40);

        jLabel7.setText("Don't have an account?");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(130, 450, 126, 16);

        showpassword.setBackground(new java.awt.Color(0, 153, 255));
        showpassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        showpassword.setText("Show password");
        showpassword.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        showpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpasswordActionPerformed(evt);
            }
        });
        jPanel3.add(showpassword);
        showpassword.setBounds(110, 310, 113, 21);

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel3.add(password);
        password.setBounds(110, 250, 281, 40);

        register.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        register.setText("Register");
        register.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerMouseClicked(evt);
            }
        });
        jPanel3.add(register);
        register.setBounds(260, 450, 64, 20);

        forgotpassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        forgotpassword.setText("Forgot password?");
        forgotpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotpasswordMouseClicked(evt);
            }
        });
        jPanel3.add(forgotpassword);
        forgotpassword.setBounds(170, 400, 129, 20);

        loginbtn.setBackground(new java.awt.Color(51, 51, 255));
        loginbtn.setText("Login");
        loginbtn.setBorder(null);
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });
        jPanel3.add(loginbtn);
        loginbtn.setBounds(40, 350, 391, 41);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(190, 20, 450, 530);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 70, 830, 580);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Uuanchai group.png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 78, 73);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 830, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
           password.requestFocusInWindow();
    }//GEN-LAST:event_emailActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
           email.requestFocusInWindow();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void showpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showpasswordActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
         loginbtn.doClick();
    }//GEN-LAST:event_passwordActionPerformed

    private void forgotpasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotpasswordMouseClicked
    this.dispose();
    new ResetPassword().setVisible(true);// TODO add your handling code here:
        
    }//GEN-LAST:event_forgotpasswordMouseClicked

    private void registerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseClicked
    this.dispose();
    new Registration().setVisible(true);// TODO add your handling code here:
 
    }//GEN-LAST:event_registerMouseClicked

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
    String emailText = email.getText().trim();
    String passwordText = new String(password.getPassword());
    
    LoginController controller = new LoginController();
    controller.handleLogin(emailText, passwordText, this);// TODO add your handling code here:
    }//GEN-LAST:event_loginbtnActionPerformed
   
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JLabel forgotpassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton loginbtn;
    public javax.swing.JPasswordField password;
    public javax.swing.JLabel register;
    public javax.swing.JRadioButton showpassword;
    // End of variables declaration//GEN-END:variables

}