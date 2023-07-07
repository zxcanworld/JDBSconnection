import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableScript1 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/trans  ";
        String username = "postgres";
        String password = "postgres";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            // Создание таблицы "users"
            statement.executeUpdate("CREATE TABLE users (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "age INTEGER NOT NULL)");
            // Создание таблицы "accounts"
            statement.executeUpdate("CREATE TABLE accounts (" +
                    "id SERIAL PRIMARY KEY," +
                    "user_id INTEGER REFERENCES users1(id)," +
                    "balance DECIMAL(10, 2) NOT NULL)");
            // Заполнение таблицы "users" данными
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('John', 25)");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Alice', 30)");
            statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Bob', 28)");
            // Заполнение таблицы "accounts" данными
            statement.executeUpdate("INSERT INTO accounts (user_id, balance) VALUES (1, 1000.00)");
            statement.executeUpdate("INSERT INTO accounts (user_id, balance) VALUES (2, 500.00)");
            statement.executeUpdate("INSERT INTO accounts (user_id, balance) VALUES (3, 750.00)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
