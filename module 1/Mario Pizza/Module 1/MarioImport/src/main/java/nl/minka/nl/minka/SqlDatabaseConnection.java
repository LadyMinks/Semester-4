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
            int addressId = updateBranchAddress(connection, mb);
            updateBranch(connection, mb, addressId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertBranchAddress(Connection connection, MarioBranch mb) throws SQLException {
        PreparedStatement psInsert = connection.prepareStatement("INSERT INTO Address (Address, PostalCode, City) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        String address = mb.getStreetName() + " " + mb.getRange();
        psInsert.setString(1, address);
        psInsert.setString(2, mb.getPostalCode());
        psInsert.setString(3, mb.getCity());
        psInsert.execute();
        ResultSet generatedKeys = psInsert.getGeneratedKeys();
        return generatedKeys.getInt(1);
    }

    public void insertBranch(Connection connection, MarioBranch mb, int addressId) throws SQLException {
        PreparedStatement psInsert = connection.prepareStatement("INSERT INTO Branch (Name, AddressId) VALUES (?,?)");
        psInsert.setString(1, mb.getBranchName());
        psInsert.setInt(2, addressId);
        psInsert.execute();
    }

    public void updateAddress(Connection connection, String columnName, String value, int id) throws SQLException {
        PreparedStatement psUpdate = connection.prepareStatement("UPDATE Address SET " + columnName + " = ? WHERE Id =  ? ");
        psUpdate.setString(1, value);
        psUpdate.setInt(2, id);

        psUpdate.executeUpdate();
    }

    public int updateBranchAddress(Connection connection, MarioBranch mb) throws SQLException {
        PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM Address WHERE (Address = ? AND PostalCode = ?) OR (Address = ? AND City = ?) OR (PostalCode = ? AND City = ?)");
        String address = mb.getStreetName() + " " + mb.getRange();
        String postalCode = mb.getPostalCode();
        String city = mb.getCity();
        psSelect.setString(1, address);
        psSelect.setString(2, postalCode);
        psSelect.setString(3, address);
        psSelect.setString(4, city);
        psSelect.setString(5, postalCode);
        psSelect.setString(6, city);

        ResultSet rs = psSelect.executeQuery();

        if (!rs.next()) {
            return insertBranchAddress(connection, mb);
        }

        if (!rs.getString("Address").equals(address)) {
            updateAddress(connection, "Address", address, rs.getInt("Id"));
            System.out.println("Updated Table Address. Column updated: Address. Row: " + rs.getInt("Id"));
            return rs.getInt("Id");
        } else if (!rs.getString("PostalCode").equals(postalCode)) {
            updateAddress(connection, "PostalCode", postalCode, rs.getInt("Id"));
            System.out.println("Updated Table Address. Column updated: PostalCode. Row: " + rs.getInt("Id"));
            return rs.getInt("Id");
        } else if (!rs.getString("City").equals(city)) {
            updateAddress(connection, "City", city, rs.getInt("Id"));
            System.out.println("Updated Table Address. Column updated: City. Row: " + rs.getInt("Id"));
            return rs.getInt("Id");
        } else {
            return rs.getInt("Id");
        }
    }

    public void updateBranchName(Connection connection, String value, int addressId) throws SQLException {
        PreparedStatement psUpdate = connection.prepareStatement("UPDATE Branch SET Name = ? WHERE AddressId =  ? ");
        psUpdate.setString(1, value);
        psUpdate.setInt(2, addressId);

        psUpdate.executeUpdate();
    }

    public void updateBranch(Connection connection, MarioBranch mb, int addressId) throws SQLException {
        PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM Branch WHERE AddressId = ?");
        psSelect.setInt(1, addressId);
        ResultSet rs = psSelect.executeQuery();

        if (!rs.next()) {
            insertBranch(connection, mb, addressId);
        }

        if (rs.getRow() == 0){
            return;
        }

        if (!rs.getString("Name").equals(mb.getBranchName())){
            updateBranchName(connection, mb.getBranchName(), addressId);
            System.out.println("Updated Table Branch. Column updated: BranchName. Id: " + rs.getInt("Id"));
        }

    }


}