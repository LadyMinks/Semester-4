package nl.minka;

/**
 * @author Minka Firth
 */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            int addressId = insertBranchAddress(connection, resultSet, mb);
            insertBranch(connection, mb, addressId);
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertBranchAddress(Connection connection, ResultSet resultSet, MarioBranch mb) throws SQLException {
        ResultSet rs = selectAddress(connection, mb);
        boolean alreadyExists = rs.next();
        if (alreadyExists) {
            return rs.getInt(1);
        }
        PreparedStatement psInsert = connection.prepareStatement("INSERT INTO Address (Address, PostalCode, City) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        String address = mb.getStreetName() + " " + mb.getRange();
        psInsert.setString(1, address);
        psInsert.setString(2, mb.getPostalCode());
        psInsert.setString(3, mb.getCity());
        psInsert.execute();
        ResultSet generatedKeys = psInsert.getGeneratedKeys();
        return generatedKeys.getInt(1);
    }

    public void printResultSet(ResultSet resultSet) {
        try {
            final int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; ++i) {
                    final Object value = resultSet.getObject(i);
                    System.out.println(value);
                }
                System.out.println();
            }
        } catch (SQLException throwables) {
            Logger.getLogger("SQLDatabaseConnection").log(Level.WARNING, "sad", throwables);
        }
    }

    public ResultSet selectAddress(Connection connection, MarioBranch mb) throws SQLException {
        PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM Address WHERE Address = ? AND PostalCode = ? AND City = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String address = mb.getStreetName() + " " + mb.getRange();
        psSelect.setString(1, address);
        psSelect.setString(2, mb.getPostalCode());
        psSelect.setString(3, mb.getCity());
        ResultSet rs = psSelect.executeQuery();
        printResultSet(rs);
        rs.beforeFirst();
        return rs;
    }

    public void insertBranch(Connection connection, MarioBranch mb, int addressId) throws SQLException {
        ResultSet rs = selectBranch(connection, mb);
        boolean alreadyExists = rs.next();
        if (alreadyExists) {
            return;
        }
        PreparedStatement psInsert = connection.prepareStatement("INSERT INTO Branch (Name, AddressId) VALUES (?,?)");
        psInsert.setString(1, mb.getBranchName());
        psInsert.setInt(2, addressId);
        psInsert.execute();
    }

    public ResultSet selectBranch(Connection connection, MarioBranch mb) throws SQLException {
        PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM Branch WHERE Name = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        psSelect.setString(1, mb.getBranchName());
        ResultSet rs = psSelect.executeQuery();
        printResultSet(rs);
        rs.beforeFirst();
        return rs;
    }
}