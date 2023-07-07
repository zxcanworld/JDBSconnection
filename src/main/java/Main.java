
        import javax.naming.ldap.PagedResultsResponseControl;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.sql.*;

        public abstract class Main {
            public static void main(String[] args) {
                Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;

                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/books", "postgres", "postgres");
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM books");

                    while (resultSet.next()) {
                        String value = resultSet.getString("auther");
                        System.out.println(value);
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null)
                            resultSet.close();
                        if (statement != null)
                            statement.close();
                        if (connection != null)
                            connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

            protected abstract void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
        }