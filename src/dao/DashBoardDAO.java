package dao;

import Database.DataBase;
import Database.MySqlConnection;
import model.FlightModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashBoardDAO {
    private DataBase db;
    
    public DashBoardDAO() {
        this.db = new MySqlConnection();
    }
    
    // Get all flights
    public List<FlightModel> getAllFlights() {
        List<FlightModel> flights = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = db.openConnection();
            if (conn == null) {
                System.err.println("❌ Database connection failed");
                return flights;
            }
            
            // Check if flights table exists
            String checkTable = "SHOW TABLES LIKE 'flights'";
            ResultSet tables = db.runQuery(conn, checkTable);
            if (tables == null || !tables.next()) {
                System.err.println("❌ Flights table doesn't exist!");
                return flights;
            }
            
            String query = "SELECT * FROM flights WHERE is_active = true ORDER BY flight_id";
            System.out.println("🔍 Executing query: " + query);
            
            ResultSet rs = db.runQuery(conn, query);
            
            if (rs != null) {
                System.out.println("📊 Found flights:");
                while (rs.next()) {
                    FlightModel flight = new FlightModel();
                    flight.setFlightId(rs.getInt("flight_id"));
                    flight.setFlightNumber(rs.getString("flight_number"));
                    flight.setAirline(rs.getString("airline"));
                    flight.setSourceCity(rs.getString("source_city"));
                    flight.setDestinationCity(rs.getString("destination_city"));
                    flight.setDepartureTime(rs.getString("departure_time"));
                    flight.setArrivalTime(rs.getString("arrival_time"));
                    flight.setDuration(rs.getString("duration"));
                    flight.setPrice(rs.getDouble("price"));
                    flight.setAvailableSeats(rs.getInt("available_seats"));
                    
                    System.out.println("  ✈️ " + flight.getFlightNumber() + ": " + 
                                     flight.getSourceCity() + " → " + flight.getDestinationCity());
                    
                    flights.add(flight);
                }
                System.out.println("✅ Total flights loaded: " + flights.size());
            } else {
                System.out.println("⚠️ No flights found or query returned null");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting flights: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
        
        return flights;
    }
}