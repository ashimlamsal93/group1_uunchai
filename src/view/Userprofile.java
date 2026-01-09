/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import model.User;
import javax.swing.*;
import java.awt.*;

public class Userprofile extends JFrame {

    private User currentUser; // <-- add this field

    // CHANGE the constructor to receive user
    public Userprofile(User user) {
        this.currentUser = user;
        initComponents();
        setTitle("User Profile - Uunchai");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        loadUserData(); // <-- call your method
    }

    // ADD this method to load data
    private void loadUserData() {
        if (currentUser != null) {
            txtUsername.setText(currentUser.getName());
            txtPhone.setText(currentUser.getPhone());
            txtEmail.setText(currentUser.getEmail());

            // Make username and email read-only
            txtUsername.setEditable(false);
            txtEmail.setEditable(false);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        profilePicLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        backArrow = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(null);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(260, 300, 0, 3);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(null);

        jLabel3.setText("Phone:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(120, 240, 70, 20);

        jLabel5.setText("Email:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(120, 280, 32, 20);

        profilePicLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-user-48.png"))); // NOI18N
        profilePicLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePicLabelMouseClicked(evt);
            }
        });
        jPanel2.add(profilePicLabel);
        profilePicLabel.setBounds(210, 60, 70, 80);

        jLabel10.setText("Username:");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(110, 200, 60, 16);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("User Profile");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(190, 150, 90, 22);
        jPanel2.add(txtUsername);
        txtUsername.setBounds(200, 200, 170, 30);
        jPanel2.add(txtPhone);
        txtPhone.setBounds(200, 240, 170, 30);

        txtEmail.addActionListener(this::txtEmailActionPerformed);
        jPanel2.add(txtEmail);
        txtEmail.setBounds(200, 280, 170, 30);

        jLabel9.setBackground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("    Exit");
        jLabel9.setOpaque(true);
        jPanel2.add(jLabel9);
        jLabel9.setBounds(140, 370, 60, 40);

        jLabel8.setBackground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("  Save");
        jLabel8.setOpaque(true);
        jPanel2.add(jLabel8);
        jLabel8.setBounds(270, 370, 60, 40);

        backArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-arrow-32.png"))); // NOI18N
        backArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backArrowMouseClicked(evt);
            }
        });
        jPanel2.add(backArrow);
        backArrow.setBounds(400, 20, 32, 32);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(170, 60, 460, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_savebtnActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed


    private void backArrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backArrowMouseClicked
this.dispose();
    // Assuming you have currentUser field in Userprofile
    new UserDashboard(currentUser).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_backArrowMouseClicked

    private void profilePicLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePicLabelMouseClicked
JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
    int result = chooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        java.io.File file = chooser.getSelectedFile();
        try {
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // adjust size
            profilePicLabel.setIcon(new ImageIcon(img));
            
            // Optional: Save path to database for persistence
            // updateUserPhotoPath(currentUser.getId(), file.getAbsolutePath());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading image");
        }
    }        // TODO add your handling code here:
    }//GEN-LAST:event_profilePicLabelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backArrow;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel profilePicLabel;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

   
