package Database;

import java.sql.*;

public class MySqlConnection implements DataBase {
    
    // LOCALHOST SETTINGS
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "W7301@jqir#"; // Empty for localhost
    
    @Override
    public Connection openConnection() {
        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Database connected: " + URL);
            return conn;
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found! Add mysql-connector-j.jar to libraries");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
            
            // Helpful error messages
            if (e.getMessage().contains("Access denied")) {
                System.err.println("💡 Check username/password in MySqlConnection.java");
            } else if (e.getMessage().contains("Unknown database")) {
                System.err.println("💡 Database 'project' doesn't exist. Create it first!");
            } else if (e.getMessage().contains("Communications link failure")) {
                System.err.println("💡 MySQL server is not running. Start MySQL service first!");
            }
            
            return null;
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            if (conn == null || conn.isClosed()) {
                System.err.println("Connection is null or closed");
                return null;
            }
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            if (conn == null || conn.isClosed()) {
                System.err.println("Connection is null or closed");
                return -1;
            }
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Update failed: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    
    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("✅ Connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}