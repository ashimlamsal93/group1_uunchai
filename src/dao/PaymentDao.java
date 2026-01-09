/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.*;

public class PaymentDao {

    public boolean savePayment(String pnr, double amount, String paymentMethod, String paymentStatus) {
    String sql = "INSERT INTO payments (pnr, amount, payment_method, payment_status) VALUES (?, ?, ?, ?)";
    try (Connection conn = MySqlConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, pnr);
        pstmt.setDouble(2, amount);
        pstmt.setString(3, paymentMethod);
        pstmt.setString(4, paymentStatus);
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    }
}