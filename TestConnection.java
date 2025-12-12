public class TestConnection {
    public static void main(String[] args) {
        if (Database.connect() != null) {
            System.out.println("Connected to SQL Server successfully!");
        } else {
            System.out.println("Connection failed.");
        }
    }
}
