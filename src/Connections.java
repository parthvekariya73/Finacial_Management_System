import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    public static class CreateUserConnection {
        public static Connection CreateUserConn (String uname) throws SQLException {
            uname = uname + "db";
            // create connection
            String user = "root";
            String pass = "";
            String url = "jdbc:mysql://localhost:3306/" + uname;
            return DriverManager.getConnection(url, user, pass);
        }
    }

    public static class ConnectionProvider {
      public static Connection CreateConnection () throws SQLException {
            // create connection
            String user = "root";
            String pass = "";
            String url = "jdbc:mysql://localhost:3306/fm_db";
            return DriverManager.getConnection(url, user, pass);

        }
    }
}
