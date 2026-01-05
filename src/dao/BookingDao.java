/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import model.Passenger;
import java.sql.*;
import java.util.List;

public class BookingDao {

    public boolean createBooking(int userId, int flightId, int numPassengers, double totalAmount, List<Passenger> passengers) {
        String pnr = "UUNCH" + flightId + String.format("%04d", (int)(Math.random() * 10000));
        Connection conn = null;
        try {
            conn = MySqlConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Insert into bookings
            String sqlBooking = "INSERT INTO bookings (pnr, user_id, flight_id, num_passengers, total_amount) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst1 = conn.prepareStatement(sqlBooking, Statement.RETURN_GENERATED_KEYS);
            pst1.setString(1, pnr);
            pst1.setInt(2, userId);
            pst1.setInt(3, flightId);
            pst1.setInt(4, numPassengers);
            pst1.setDouble(5, totalAmount);
            pst1.executeUpdate();

            // Get generated booking_id
            ResultSet keys = pst1.getGeneratedKeys();
            int bookingId = keys.next() ? keys.getInt(1) : -1;

            // 2. Insert passengers
            String sqlPassenger = "INSERT INTO passengers (booking_id, full_name, age, gender) VALUES (?, ?, ?, ?)";
            PreparedStatement pst2 = conn.prepareStatement(sqlPassenger);
            for (Passenger p : passengers) {
                pst2.setInt(1, bookingId);
                pst2.setString(2, p.getFullName());
                pst2.setInt(3, p.getAge());
                pst2.setString(4, p.getGender());
                pst2.addBatch();
            }
            pst2.executeBatch();

            // 3. Reduce available seats
            String sqlSeats = "UPDATE flights SET available_seats = available_seats - ? WHERE flight_id = ? AND available_seats >= ?";
            PreparedStatement pst3 = conn.prepareStatement(sqlSeats);
            pst3.setInt(1, numPassengers);
            pst3.setInt(2, flightId);
            pst3.setInt(3, numPassengers);
            int updated = pst3.executeUpdate();
            if (updated == 0) {
                throw new SQLException("Not enough seats available");
            }

            conn.commit();
            System.out.println("Booking saved successfully! PNR: " + pnr);
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) {}
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException ex) {}
            }
        }
    }
}