/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.User;
import database.MySqlConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookingHistory extends JFrame {

    private User currentUser;

    public BookingHistory(User user) {
        this.currentUser = user;
        initComponents();
        setTitle("My Booking History - Uunchai ");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "User not logged in");
            dispose();
            return;
        }

        loadBookings();
    }

    private void loadBookings() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("PNR");
        model.addColumn("Flight");
        model.addColumn("From → To");
        model.addColumn("Departure Date");
        model.addColumn("Travellers");
        model.addColumn("Total Amount");
        model.addColumn("Status");

        String sql = """
            SELECT b.pnr, f.flight_number, f.airline_name, f.source, f.destination,
                   b.booking_date, b.num_passengers, b.total_amount, b.status
            FROM bookings b
            JOIN flights f ON b.flight_id = f.flight_id
            WHERE b.user_id = ?
            ORDER BY b.booking_date DESC
            """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, currentUser.getId());
            ResultSet rs = pst.executeQuery();

            boolean hasBookings = false;
            while (rs.next()) {
                hasBookings = true;
                model.addRow(new Object[]{
                    rs.getString("pnr"),
                    rs.getString("flight_number") + " (" + rs.getString("airline_name") + ")",
                    rs.getString("source") + " → " + rs.getString("destination"),
                    rs.getDate("booking_date"),
                    rs.getInt("num_passengers"),
                    "NPR " + String.format("%.2f", rs.getDouble("total_amount")),
                    rs.getString("status")
                });
            }

            if (!hasBookings) {
                JOptionPane.showMessageDialog(this, "No bookings found.");
            }

            jTableBookings.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading bookings");
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBookings = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        getContentPane().setLayout(null);

        jTableBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PNR", "Flight", "Date", "Travellers", "Total Amount", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTableBookings);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 790, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBookings;
    // End of variables declaration//GEN-END:variables
}
