package database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DataBase {
    Connection openConnection();
    void closeConnection(Connection conn);
    ResultSet runQuery(Connection conn, String query);
    int executeUpdate(Connection conn, String query);
}