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

    public List<FlightModel> getAllHistory(int userId) {
        List<FlightModel> flightList = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ? ORDER BY id DESC";

       
        try (Connection conn = database.openConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                flightList.add(new FlightModel(
                    rs.getString("flight_from"),
                    rs.getString("flight_to"),
                    rs.getString("flight_date"),
                    rs.getString("dep_time"),
                    rs.getString("arr_time")
                ));
            }
        } catch (SQLException e) {
            System.out.println("DAO Error: " + e.getMessage());
        }
        return flightList;
    }
}