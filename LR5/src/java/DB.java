import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    public static void executeSelect() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("lastname: " + resultSet.getString("lastname"));
                System.out.println("firstname: " + resultSet.getString("firstname"));
                System.out.println("middlename: " + resultSet.getString("middlename"));
                System.out.println("phone: " + resultSet.getString("phone"));
                System.out.println("telegram: " + resultSet.getString("telegram"));
                System.out.println("email: " + resultSet.getString("email"));
                System.out.println("git: " + resultSet.getString("git"));
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}