/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Database.MySqlConnection;
import java.sql.*;

public class PaymentDao {

    public boolean savePayment(String pnr, double amount) {
        String sql = "INSERT INTO payments (pnr, amount, payment_status) VALUES (?, ?, 'Success')";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, pnr);
            pst.setDouble(2, amount);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}