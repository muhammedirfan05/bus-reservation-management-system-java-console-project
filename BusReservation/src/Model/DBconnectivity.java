package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconnectivity 
{
    private static volatile Connection connection;
    private DBconnectivity() {} 
    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/busreservation",
                        "root",
                        "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
