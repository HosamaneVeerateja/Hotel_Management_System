
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Replace with your database credentials
            String url = "jdbc:mysql://localhost:3306/hotel_management"; // Update if necessary
            String user = "one"; // Your database username
            String password = "one"; // Your database password
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
