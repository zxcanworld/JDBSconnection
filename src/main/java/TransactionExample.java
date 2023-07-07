import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExample {
        public static void main(String[] args) {
            String url = "jdbc:postgresql://localhost:5432/books";
            String username = "postgres";
            String password = "postgres";
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                connection.setAutoCommit(false); // Отключаем автокоммит, чтобы начать транзакцию
                try {
                    // Выполняем операции в рамках транзакции
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("INSERT INTO users (name, age) VALUES ('John', 25)");
                    statement.executeUpdate("UPDATE accounts SET balance = balance - 100 WHERE user_id = 1");
                    connection.commit(); // Фиксируем изменения
                } catch (SQLException e) {
                    connection.rollback(); // Откатываем транзакцию в случае ошибки
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
