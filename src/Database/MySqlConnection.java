package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlConnection implements DataBase {
    
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "W7301@jqir#"; // Change to your MySQL password
    
    @Override
    public Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            if (connection != null && !connection.isClosed()) {
                System.out.println("✓ Database connection established successfully");
                return connection;
            } else {
                System.out.println("✗ Database connection failed");
                return null;
            }
        } catch (Exception e) {
            System.err.println("✗ Connection error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            if (conn == null || conn.isClosed()) {
                System.err.println("✗ Connection is null or closed");
                return null;
            }
            Statement stmp = conn.createStatement();
            return stmp.executeQuery(query);
        } catch (Exception e) {
            System.err.println("✗ Query error: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            if (conn == null || conn.isClosed()) {
                System.err.println("✗ Connection is null or closed");
                return -1;
            }
            Statement stmp = conn.createStatement();
            return stmp.executeUpdate(query);
        } catch (Exception e) {
            System.err.println("✗ Update error: " + e.getMessage());
            return -1;
        }
    }
    
    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("✓ Database connection closed");
            }
        } catch (Exception e) {
            System.err.println("✗ Close error: " + e.getMessage());
        }
    }
}