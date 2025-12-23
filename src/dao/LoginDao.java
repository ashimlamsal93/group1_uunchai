package dao;

import Database.DataBase;
import Database.MySqlConnection;
import model.UserModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                return null;
            }
            
            String query = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'";
            ResultSet rs = db.runQuery(conn, query);
            
            if (rs != null && rs.next()) {
                UserModel user = new UserModel();
                user.setUserId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
            
        } catch (SQLException e) {
            return null;
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
    }
    
    public boolean checkEmailExists(String email) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            String query = "SELECT COUNT(*) as count FROM users WHERE email = '" + email + "'";
            ResultSet rs = db.runQuery(conn, query);
            
            if (rs != null && rs.next()) {
                return rs.getInt("count") > 0;
            }
            return false;
            
        } catch (SQLException e) {
            return false;
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
    }
    
    public boolean registerUser(String email, String password) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            
            if (checkEmailExists(email)) {
                return false;
            }
            
            String query = String.format(
                "INSERT INTO users (email, password) VALUES ('%s', '%s')",
                email, password
            );
            
            int rowsAffected = db.executeUpdate(conn, query);
            return rowsAffected > 0;
            
        } catch (Exception e) {
            return false;
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
    }
    
    public boolean resetPassword(String email, String newPassword) {
        Connection conn = null;
        try {
            conn = db.openConnection();
            
            if (!checkEmailExists(email)) {
                return false;
            }
            
            String query = "UPDATE users SET password = '" + newPassword + "' WHERE email = '" + email + "'";
            int rowsAffected = db.executeUpdate(conn, query);
            return rowsAffected > 0;
            
        } catch (Exception e) {
            return false;
        } finally {
            if (conn != null) {
                db.closeConnection(conn);
            }
        }
    }
}