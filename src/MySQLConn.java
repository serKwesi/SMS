import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConn {
    
    public static Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/protechdatabase", "root", "charlesobeng.98");

    }


}


