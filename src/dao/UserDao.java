/*umpoer
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;
import database.MySqlConnection;  // Fixed package name
import model.User;
import java.sql.*;

public class UserDao {

    // Register new user WITH security question
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, email, password, phone, role, SecurityQuestion, SecurityAnswer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
           
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getRole());
            pst.setString(6, user.getSecurityQuestion());
            pst.setString(7, user.getSecurityAnswer());
           
            int result = pst.executeUpdate();
            return result > 0;
           
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   
    // Login user
    public User loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
           
            pst.setString(1, email);
            pst.setString(2, password);
           
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setSecurityQuestion(rs.getString("SecurityQuestion"));
                user.setSecurityAnswer(rs.getString("SecurityAnswer"));
                return user;
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    // Check if email exists
    public boolean emailExists(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
           
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            return rs.next();
           
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   
    // Get security question by email
    public String getSecurityQuestion(String email) {
        String query = "SELECT SecurityQuestion FROM users WHERE email = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
           
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
           
            if (rs.next()) {
                return rs.getString("SecurityQuestion");
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    // Verify security answer
    public boolean verifySecurityAnswer(String email, String question, String answer) {
        String query = "SELECT 1 FROM users WHERE email = ? AND SecurityQuestion = ? AND LOWER(TRIM(SecurityAnswer)) = LOWER(TRIM(?))";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
          
            pst.setString(1, email.trim());
            pst.setString(2, question.trim());
            pst.setString(3, answer.trim().toLowerCase());
          
            ResultSet rs = pst.executeQuery();
            return rs.next();
          
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   
    // Update password
    public boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE email = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
           
            pst.setString(1, newPassword);
            pst.setString(2, email);
           
            int result = pst.executeUpdate();
            return result > 0;
           
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === ADD THIS METHOD ===
    public ResultSet getAllUsers() {
        String query = "SELECT id, username, email, phone, role FROM users ORDER BY id";
        try {
            Connection conn = MySqlConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}