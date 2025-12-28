package dao;

import Database.DataBase;
import Database.MySqlConnection;
import model.UserModel;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginDao {
    private DataBase db;
    
    public LoginDao() {
        this.db = new MySqlConnection();
    }
    
    public UserModel authenticateUser(String email, String password) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            if (conn == null) {
                System.err.println("❌ Database connection failed");
                return null;
            }
            
            // Check if users table exists
            String checkTableQuery = "SHOW TABLES LIKE 'users'";
            ResultSet tables = db.runQuery(conn, checkTableQuery);
            if (tables == null || !tables.next()) {
                System.err.println("❌ Users table doesn't exist!");
                return null;
            }
            
            // Check table structure
            String describeQuery = "DESCRIBE users";
            ResultSet rsDesc = db.runQuery(conn, describeQuery);
            System.out.println("📋 Users table structure:");
            while (rsDesc != null && rsDesc.next()) {
                System.out.println("  " + rsDesc.getString("Field") + " : " + rsDesc.getString("Type"));
            }
            
            // First try: check with email and password
            String query = "SELECT email, password FROM users WHERE email = '" + email + "' AND password = '" + password + "'";
            System.out.println("🔍 Executing query: " + query);
            
            ResultSet rs = db.runQuery(conn, query);
            
            if (rs != null && rs.next()) {
                UserModel user = new UserModel();
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                
                System.out.println("✅ User found: " + user.getEmail());
                return user;
            }
            
            System.out.println("❌ No user found with these credentials");
            return null;
            
        } catch (Exception e) {
            System.err.println("❌ Error in authenticateUser: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
    }
    
    public void testConnection() {
        System.out.println("Testing database connection...");
        Connection conn = db.openConnection();
        if (conn != null) {
            System.out.println("✅ Database connection OK");
            db.closeConnection(conn);
        } else {
            System.err.println("❌ Database connection FAILED");
        }
    }
}