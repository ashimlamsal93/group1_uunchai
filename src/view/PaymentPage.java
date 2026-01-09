/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.PaymentDao;
import javax.swing.*;
import java.awt.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class PaymentPage extends JFrame {

    private double totalAmount;
    private String pnr;
    private String route;
    private String time;
    private String passengerName;
    private int travellers;

    // Replace with your Stripe TEST secret key (from dashboard.stripe.com → Developers → API keys)
    //private static final String STRIPE_SECRET_KEY = "sk_test_51SnfKdJYggDwdKZT4TirOAwalNbH5icb8E05BIby3G4HO7Ija46HXwGSaBxabluIC3UBHn6RSSx05JDklA5E1Cqv00eceYdA1O";  // ← PUT YOUR TEST SECRET KEY HERE

    public PaymentPage(String pnr, double totalAmount, String route, String time, String passengerName, int travellers) {
        this.pnr = pnr;
        this.totalAmount = totalAmount;
        this.route = route;
        this.time = time;
        this.passengerName = passengerName;
        this.travellers = travellers;

        initComponents();
        setTitle("Payment - Uunchai");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Initialize Stripe
        //Stripe.apiKey = STRIPE_SECRET_KEY;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblTotalLabel = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        btnStripePay = new javax.swing.JButton();
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

        btnStripePay.setBackground(new java.awt.Color(51, 153, 255));
        btnStripePay.setText("Pay With Stripe");
        btnStripePay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStripePayActionPerformed(evt);
            }
        });
        jPanel2.add(btnStripePay);
        btnStripePay.setBounds(210, 180, 150, 40);

        btnCancel.setBackground(new java.awt.Color(51, 153, 255));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancel);
        btnCancel.setBounds(240, 250, 90, 30);

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
private void payWithStripe() {
        try {
            System.out.println("1. Starting Stripe payment...");
            System.out.println("Total amount: " + totalAmount);

            long amountInSmallestUnit = Math.round(totalAmount * 100);
            System.out.println("Amount in smallest unit: " + amountInSmallestUnit);  //paise

            SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success?pnr=" + pnr)
                .setCancelUrl("http://localhost:8080/cancel")
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("npr")
                                .setUnitAmount(amountInSmallestUnit)
                                .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Flight Booking - PNR: " + pnr)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build();

            System.out.println("2. Creating Stripe session...");
            Session session = Session.create(params);

            String checkoutUrl = session.getUrl();
            System.out.println("3. Stripe Checkout URL: " + checkoutUrl);

            // Open browser - Windows cmd method (reliable, no double tab)
            System.out.println("4. Opening browser...");
            Runtime.getRuntime().exec("cmd /c start \"\" \"" + checkoutUrl + "\"");

            JOptionPane.showMessageDialog(this,
                "Redirecting to Stripe...\nIf browser doesn't open, copy URL:\n" + checkoutUrl,
                "Info", JOptionPane.INFORMATION_MESSAGE);

            // For demo: Save payment (in real app use webhook)
            PaymentDao paymentDao = new PaymentDao();
            boolean saved = paymentDao.savePayment(pnr, totalAmount, "Stripe", "Success");

            if (saved) {
                JOptionPane.showMessageDialog(this,
                    "Payment Successful via Stripe!\n\n" +
                    "PNR: " + pnr + "\n" +
                    "Total Paid: NPR " + String.format("%.2f", totalAmount),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save payment record", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Payment error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void txtTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAmountActionPerformed

    private void btnStripePayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStripePayActionPerformed
    payWithStripe();
    }//GEN-LAST:event_btnStripePayActionPerformed
 
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
int choice = JOptionPane.showConfirmDialog(this, "Cancel payment?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnStripePay;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalLabel;
    private javax.swing.JTextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}
