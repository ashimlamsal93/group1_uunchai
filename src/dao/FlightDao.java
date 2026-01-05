/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Database.MySqlConnection;
import java.sql.*;

public class FlightDao {

    public boolean addFlight(String flightNumber, String airlineName, String source, String destination,
                             String departure, String arrival, double price, int totalSeats) {
        String sql = "INSERT INTO flights (flight_number, airline_name, source, destination, " +
                     "departure_datetime, arrival_datetime, price, total_seats, available_seats) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, flightNumber);
            pst.setString(2, airlineName);
            pst.setString(3, source);
            pst.setString(4, destination);
            pst.setString(5, departure + ":00");  // assuming input is YYYY-MM-DD HH:mm
            pst.setString(6, arrival + ":00");
            pst.setDouble(7, price);
            pst.setInt(8, totalSeats);
            pst.setInt(9, totalSeats);  // available = total initially

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
public ResultSet searchFlights(String from, String to, String date) {
    String query = "SELECT * FROM flights WHERE source = ? AND destination = ? AND DATE(departure_datetime) = ?";
    try {
        PreparedStatement pst = MySqlConnection.getConnection().prepareStatement(query);
        pst.setString(1, from);
        pst.setString(2, to);
        pst.setString(3, date);
        return pst.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}
}
