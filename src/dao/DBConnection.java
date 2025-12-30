/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DELL
 */
public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/airticket",
                "root",
                "12345"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}


    
