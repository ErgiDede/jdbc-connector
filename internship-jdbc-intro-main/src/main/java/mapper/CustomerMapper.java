package mapper;

import model.entity.Customer;
import model.entity.Order;
import util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper extends Mapper<Customer> {

    @Override
    public Customer map(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setId(result.getInt("customerNumber"));
        customer.setCustomerName(result.getString("customerName"));
        customer.setContactLastName(result.getString("contactLastName"));
        customer.setContactFirstName(result.getString("contactFirstName"));
        customer.setPhone(result.getString("phone"));
        customer.setAddressLine1(result.getString("addressLine1"));
        customer.setAddressLine2(result.getString("addressLine2"));
        customer.setCity(result.getString("city"));
        customer.setState(result.getString("state"));
        customer.setPostalCode(result.getString("postalCode"));
        customer.setCountry(result.getString("country"));
        customer.setSalesRepEmployeeNumber(result.getInt("salesRepEmployeeNumber"));
        customer.setCreditLimit(result.getDouble("creditLimit"));
        return customer;
    }
}


