import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class StaffDAO {
    
    private final Connection connection;
    
    public StaffDAO() throws SQLException {
        this.connection = MySQLConn.getConnection();
    }
    
    
    public boolean authenticateStaff(String email, String password) {
        String query = "SELECT * FROM staff WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a row is returned, authentication is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}

