/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import model.PassengerModel;

public class PassengerDAO {
    private Connection conn;

    public PassengerDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertBooking(PassengerModel p) {
        String sql = "INSERT INTO Passengers (title, full_name, age, traveller, contact, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, p.getTitle());
            pst.setString(2, p.getFullName());
            pst.setInt(3, p.getAge());
            pst.setString(4, p.getTraveller());
            pst.setString(5, p.getContact());
            pst.setString(6, p.getEmail());
            
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}