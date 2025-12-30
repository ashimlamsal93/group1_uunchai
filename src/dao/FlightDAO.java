/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.FlightModel;
import database.Database;
import database.MySqlConnection;

public class FlightDAO {
    private final Database database;
    
    public FlightDAO() {
        this.database = new MySqlConnection();
    }

    // 1. History Fetch garne Method (Sachiye ko)
    public List<FlightModel> getAllHistory(int userId) {
        List<FlightModel> flightList = new ArrayList<>();
        
        // Sachaunu पर्ने kura: user_id ko thau ma 'id' fetch garnu parcha cancel garna ko lagi
        String query = "SELECT id, flight_from, flight_to, flight_date, dep_time, arr_time, status FROM bookings WHERE user_id = ? ORDER BY id DESC";

        try (Connection conn = database.openConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // FlightModel(id, from, to, date, dep, arr, status)
                flightList.add(new FlightModel(
                    rs.getInt("id"),         // Unique Booking ID liyeko
                    rs.getString("flight_from"),
                    rs.getString("flight_to"),
                    rs.getString("flight_date"),
                    rs.getString("dep_time"),
                    rs.getString("arr_time"),
                    rs.getString("status")    // Current status (BOOKED/CANCELLED)
                ));
            }
        } catch (SQLException e) {
            System.out.println("DAO Fetch Error: " + e.getMessage());
        }
        return flightList;
    }

    // 2. Flight Cancel garne Method (Update Query)
    public boolean cancelFlightById(int bookingId) {
        String query = "UPDATE bookings SET status = 'CANCELLED' WHERE id = ?";
        
        try (Connection conn = database.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, bookingId);
            int rowsAffected = pstmt.executeUpdate();
            
            // Yadi record update bhayo bhane true return garchha
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("DAO Update Error: " + e.getMessage());
            return false;
        }
    }
}