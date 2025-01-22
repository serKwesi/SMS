import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomerAuthenDAO {
    
    
        private final Connection connection;
        
            
        /**
         * @param
         * @throws SQLException
         */
        public CustomerAuthenDAO(Connection connection) throws SQLException {
            this.connection = MySQLConn.getConnection();
        }
        
        /**
         *
         * @param customerID
         * @param emailaddress
         * @param password
         * @throws SQLException
         */
        public void insertCustomerAuthen(int customerID, String emailaddress, String password) throws SQLException {
            String query = "INSERT INTO CustomerAuthentication (customer_id, email_address, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, customerID);
                statement.setString(2, emailaddress);
                statement.setString(3, password);
                
                statement.executeUpdate();
                          
                //System.out.println("Customer inserted successfully.");
            } catch (SQLException e) {
            System.err.println("Failed to insert customer: " + e.getMessage());}
        }
        
        public boolean authenticateCustomer(String emailaddress, String password) {
            String query = "SELECT * FROM CustomerAuthentication WHERE emailaddress = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, emailaddress);
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
    
