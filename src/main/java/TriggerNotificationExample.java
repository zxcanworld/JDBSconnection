import java.sql.*;
    public class TriggerNotificationExample {
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
                // Create the notifications table if it doesn't exist
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS notifications (payload text)");
                // Wait for notifications
                statement.execute("LISTEN my_notification");
                System.out.println("Waiting for notifications...");
                while (true) {
                    // Check for notifications
                    statement.execute("SELECT 1");
                    // Retrieve notifications
                    resultSet = statement.executeQuery("SELECT payload FROM notifications");
                    while (resultSet.next()) {
                        String payload = resultSet.getString("payload");
                        System.out.println("User added: " + payload);
                    }
                    Thread.sleep(1000); // Wait for 1 second
                }
            } catch (SQLException | InterruptedException e) {
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
