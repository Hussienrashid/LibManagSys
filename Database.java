import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB2;encrypt=false";
    private static final String USER = "sa";  // your SQL username
    private static final String PASS = "123456"; // your password

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
