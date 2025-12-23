/*umpoer
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;
import database.Database;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserModel;

public class UserDao {

    private final Database database;

    public UserDao() {
        this.database = new MySqlConnection();
    }

    // ✅ REGISTER USER
    public boolean register(UserModel user) {
        Connection conn = database.openConnection();
        String sql = "INSERT INTO users (username, email, password, contact) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getContact()); // contact as VARCHAR

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            database.closeConnection(conn);
        }
    }

    // ✅ CHECK USER EXISTS
   public boolean exists(UserModel usermodel) {
    Connection conn = database.openConnection();

    if (conn == null) {
        System.err.println("Connection is null. Check DB credentials.");
        return false;
    }

    String query = "SELECT 1 FROM users WHERE username = ? OR email = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, usermodel.getUsername());
        pstmt.setString(2, usermodel.getEmail());

        ResultSet rs = pstmt.executeQuery();
        return rs.next();

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        database.closeConnection(conn);
    }
   }
}
