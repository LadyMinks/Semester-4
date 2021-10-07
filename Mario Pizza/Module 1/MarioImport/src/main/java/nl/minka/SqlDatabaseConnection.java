package nl.minka;

/**
 * @author Minka Firth
 */

import java.sql.*;

public class SqlDatabaseConnection {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public void connect(MarioBranch mb) {
        String connectionUrl =
                "jdbc:sqlserver://localhost;"
                        + "database=dominospizzas;"
                        + "user=mario;"
                        + "password=pizza;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM City WHERE Id = ? ");
            resultSet = insertBranchAddress(connection, resultSet, mb);
//
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public ResultSet insertBranchName(Connection connection, ResultSet resultSet, String sql) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Branch (Name, AddressId) VALUES (?, ?) ");
//        preparedStatement.setString(1, sql);
//        preparedStatement.setInt(2, 1);
//        resultSet = preparedStatement.executeQuery();
//        return resultSet;
//    }

    public ResultSet insertBranchAddress(Connection connection, ResultSet resultSet, MarioBranch mb) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Address (Address, PostalCode, City) VALUES (?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
        String address = mb.getStreetName() + " " + mb.getRange();
        ps.setString(1, address);
        ps.setString(2, mb.getPostalCode());
        ps.setString(3, mb.getCity());
        ps.execute();
        resultSet = ps.getGeneratedKeys();
        return resultSet;
    }
}