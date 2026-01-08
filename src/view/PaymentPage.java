/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.PaymentDao;
import javax.swing.*;
import java.awt.*;

public class PaymentPage extends JFrame {

    private double totalAmount;
    private String pnr;
    private String route;
    private String time;
    private String passengerName;
    private int travellers;

    public PaymentPage(String pnr, double totalAmount, String route, String time, String passengerName, int travellers) {
        this.pnr = pnr;
        this.totalAmount = totalAmount;
        this.route = route;
        this.time = time;
        this.passengerName = passengerName;
        this.travellers = travellers;

        initComponents();
        setTitle("Payment - Uunchai Airlines");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblTotalLabel = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCardNumber = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtExpiry = new javax.swing.JTextField();
        txtCVV = new javax.swing.JTextField();
        btnPayNow = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(113, 171, 255));
        jPanel2.setLayout(null);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setText("Payment");
        jPanel2.add(lblTitle);
        lblTitle.setBounds(240, 20, 90, 30);

        lblTotalLabel.setText("Total Amount:");
        jPanel2.add(lblTotalLabel);
        lblTotalLabel.setBounds(90, 100, 90, 30);

        txtTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalAmountActionPerformed(evt);
            }
        });
        jPanel2.add(txtTotalAmount);
        txtTotalAmount.setBounds(190, 90, 260, 40);

        jLabel3.setText("Card Number:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(90, 140, 90, 30);
        jPanel2.add(txtCardNumber);
        txtCardNumber.setBounds(190, 140, 260, 40);

        jLabel4.setText("Expiry Date:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(100, 190, 70, 30);

        jLabel5.setText("CVV:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(130, 240, 40, 30);
        jPanel2.add(txtExpiry);
        txtExpiry.setBounds(190, 190, 260, 40);
        jPanel2.add(txtCVV);
        txtCVV.setBounds(190, 240, 260, 40);

        btnPayNow.setBackground(new java.awt.Color(51, 153, 255));
        btnPayNow.setText("Pay Now");
        btnPayNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayNowActionPerformed(evt);
            }
        });
        jPanel2.add(btnPayNow);
        btnPayNow.setBounds(230, 320, 130, 30);

        btnCancel.setBackground(new java.awt.Color(51, 153, 255));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancel);
        btnCancel.setBounds(250, 380, 90, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(120, 50, 600, 470);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAmountActionPerformed

    private void btnPayNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayNowActionPerformed
   String card = txtCardNumber.getText().trim();
    if (card.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter card number");
        return;
    }

    PaymentDao paymentDao = new PaymentDao();
    boolean saved = paymentDao.savePayment(pnr, totalAmount);

    if (saved) {
        JOptionPane.showMessageDialog(this,
            "Payment Successful!\n\n" +
            "PNR: " + pnr + "\n" +
            "Total Paid: NPR " + String.format("%.2f", totalAmount) + "\n\n" +
            "Payment saved to database!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Payment failed");
    }

    dispose();
    }//GEN-LAST:event_btnPayNowActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
int choice = JOptionPane.showConfirmDialog(this, "Cancel payment?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPayNow;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalLabel;
    private javax.swing.JTextField txtCVV;
    private javax.swing.JTextField txtCardNumber;
    private javax.swing.JTextField txtExpiry;
    private javax.swing.JTextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}
