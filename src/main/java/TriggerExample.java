import java.sql.*;

public class TriggerExample {
        public static void main(String[] args) {
            // JDBC connection parameters
            String url = "jdbc:postgresql://localhost:5432/books";
            String username = "postgres";
            String password = "postgres";
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                // Establish the connection
                connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();
                // Create a trigger
                String createTriggerQuery = "CREATE OR REPLACE FUNCTION trigger_function() RETURNS TRIGGER AS $$\n" +
                        "BEGIN\n" +
                        "    -- Your trigger logic here\n" +
                        "    -- You can perform operations on the affected rows or tables\n" +
                        "    -- For example, you can select data and display it\n" +
                        "    RAISE NOTICE 'Trigger fired: %', NEW.name;\n" +
                        "    RETURN NEW;\n" +
                        "END;\n" +
                        "$$ LANGUAGE plpgsql;\n" +
                        "\n" +
                        "CREATE TRIGGER my_trigger\n" +
                        "AFTER INSERT ON users1\n" +
                        "FOR EACH ROW\n" +
                        "EXECUTE FUNCTION trigger_function();";
                statement.executeUpdate(createTriggerQuery);
                // Insert a row to trigger the trigger
                String insertQuery = "INSERT INTO users1 m       (name, age) VALUES ('Vasily', 30);";
                statement.executeUpdate(insertQuery);
                // Retrieve the result of the trigger
                String selectQuery = "SELECT * FROM users1;";
                resultSet = statement.executeQuery(selectQuery);
                // Print the result
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Close the resources
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}
