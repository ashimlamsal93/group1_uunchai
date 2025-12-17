/*umpoer
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;
import database.Database;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import model.UserModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author A plus
 */
public class UserDao {
    private Database database;
    
    public UserDao() {
        this.database = new MySqlConnection();
    }
    
    public boolean Register(UserModel user) {
        Connection conn = database.openConnection();
        String query = "INSERT INTO users (username,contact,email, password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getContact());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            int rows = pstmt.executeUpdate();
            pstmt.close();
            
            return rows > 0;
            
       } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        database.closeConnection(conn);
    }
}
    
    public UserModel getUserByUsername(String username) {
        Connection conn = database.openConnection();
        String query = "SELECT * FROM users WHERE username = ?";
        UserModel user = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
           ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserModel();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection(conn);
        }
        return user;
    }

    public boolean check(UserModel usermodel) {
    Connection conn = database.openConnection();
    String query = "SELECT 1 FROM users WHERE username = ? OR email = ?";

    try {
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, usermodel.getUsername());
        pstmt.setString(2, usermodel.getEmail());

        ResultSet rs = pstmt.executeQuery();
        boolean exists = rs.next(); // true if any record found

        rs.close();
        pstmt.close();

        return exists;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        database.closeConnection(conn);
    }
}

}



