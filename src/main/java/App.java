import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class App {

    public static void main(String[] args)  {
        String  jdbcDriver = "org.postgresql.Driver";
        String jdbcURL="jdbc:postgresql://java-angular.cwfmt6u6yvg4.us-east-2.rds.amazonaws.com:5432/postgres?Schema=public";
        String username="postgres";
        String password="Cold2day!";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            if (conn != null) {
                System.out.println("Connection successful!");
                conn.close();

            }

        }catch (ClassNotFoundException e) {
           System.err.println("Could not find PostgreSQL JDBC driver. Connection attempt aborted.");

        }catch (SQLException e) {
            System.err.println("Could not establish a connection to the database.");
            e.printStackTrace();




        }





    }

}
