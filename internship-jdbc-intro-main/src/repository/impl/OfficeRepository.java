package repository.impl;

import mapper.EmployeeMapper;
import mapper.OfficeMapper;
import model.entity.Customer;
import model.entity.Employee;
import model.entity.Office;
import util.JdbcConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfficeRepository extends BaseRepository<Office, String>{

    public OfficeRepository() {
        super(new OfficeMapper());
    }

    @Override
    public List<Office> findAll() {
        List<Office> offices = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_OFFICES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Office office = getMapper().map(result);
                offices.add(office);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return offices;
    }


    @Override
    public Office findById(String id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_OFFICE_BY_ID)) {
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    @Override
    public Boolean exists(String officeCode) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.CHECK_IF_OFFICE_EXISTS)) {
            statement.setString(1, officeCode);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }


    @Override
    public Boolean save(Office office) {
        /*
         * TODO: Implement a method which adds an office to the offices table
         *  If the office exists then the method should instead update the office
         *
         */
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.INSERT_INTO_OFFICE)) {
            statement.setString(1, office.getId());
            statement.setString(2, office.getCity());
            statement.setString(3, office.getPhone());
            statement.setString(4, office.getAddressLine1());
            statement.setString(5, office.getAddressLine2());
            statement.setString(6, office.getState());
            statement.setString(7, office.getCountry());
            statement.setString(8, office.getPostalCode());
            statement.setString(9, office.getTerritory());
            int numRowsInserted = statement.executeUpdate();
            System.out.println(numRowsInserted + " row(s) inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return true;
    }

    @Override
    public Integer update(Office office) {
        /*
         * TODO: Implement a method which updates an office with the given Office instance
         *  The method should then return the number of updated records
         */
        int numRecordsUpdated = 0;
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement("UPDATE offices SET city = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, state = ?, country = ?, postalCode = ?, territory = ? WHERE officeCode = ?")) {
            statement.setString(1, office.getCity());
            statement.setString(2, office.getPhone());
            statement.setString(3, office.getAddressLine1());
            statement.setString(4, office.getAddressLine2());
            statement.setString(5, office.getState());
            statement.setString(6, office.getCountry());
            statement.setString(7, office.getPostalCode());
            statement.setString(8, office.getTerritory());
            statement.setString(9, office.getId());
            numRecordsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return numRecordsUpdated;
    }

    @Override
    public Customer findById(int id) {
        return null;
    }
}
