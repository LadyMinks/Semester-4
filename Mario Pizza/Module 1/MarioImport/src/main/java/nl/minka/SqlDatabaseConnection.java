package nl.minka;

/**
 * @author Minka Firth
 */

import java.sql.*;

public class SqlDatabaseConnection {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost;"
                        + "database=dominospizzas;"
                        + "user=mario;"
                        + "password=pizza;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            // Code here.
            Statement statement = connection.createStatement();{
                String selectSql = "SELECT Id, Name, address_id FROM branch";
                resultSet = statement.executeQuery(selectSql);
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}