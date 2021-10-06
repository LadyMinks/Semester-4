package nl.minka;

/**
 * @author Minka Firth
 */

import java.sql.*;

public class SqlDatabaseConnection {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public void connect() {
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM City WHERE Id = ? ");
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBranchName(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Branch (Name) VALUES ? ");
        preparedStatement.setString(1, );
    }
}