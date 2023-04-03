package repository.impl;

import mapper.EmployeeMapper;
import model.entity.Customer;
import model.entity.Employee;
import util.JdbcConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository extends BaseRepository<Employee, Integer> {

    public EmployeeRepository() {
        super(new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_EMPLOYEES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Employee employee = getMapper().map(result);
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.CHECK_IF_EMPLOYEE_EXISTS)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return false; // Return false if an error occurred or there was no match in the database
    }

    @Override
    public Boolean save(Employee employee) {
        /*
         * TODO: Implement a method which adds an employee to the employees table
         *  If the employee exists then the method should instead update the employee
         *
         */
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getExtension());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getOfficeCode());
            statement.setInt(7, employee.getReportsTo());
            statement.setString(8, employee.getJobTitle());
            int numRowsInserted = statement.executeUpdate();
            System.out.println(numRowsInserted + " row(s) inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return true;
    }

    @Override
    public Integer update(Employee employee) {
        /*
          * TODO: Implement a method which updates an employee with the given Employee instance
          *  The method should then return the number of updated records
         */
        int numRecordsUpdated = 0;
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement("UPDATE employees SET lastName = ?, firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?")) {
            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getExtension());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getOfficeCode());
            statement.setInt(6, employee.getReportsTo());
            statement.setString(7, employee.getJobTitle());
            statement.setInt(8, employee.getId());
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
