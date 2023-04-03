package repository.impl;

import mapper.CustomerMapper;
import mapper.OrderMapper;
import model.entity.Customer;
import model.entity.Order;
import util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository extends BaseRepository<Customer, Integer>{

    public CustomerRepository() {
        super(new CustomerMapper());
    }


    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(Integer Id) {
        Customer customers = null;
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement("SELECT c.*, o.* FROM customers c LEFT JOIN orders o ON c.customerNumber = o.customerNumber WHERE c.customerNumber = ?")) {
            statement.setInt(1, Id);
            ResultSet result = statement.executeQuery();
            Map<Integer, Customer> customerMap = new HashMap<>();
            while (result.next()) {
                int customerId = result.getInt("customerNumber");
                customers = customerMap.get(customerId);
                if (customers == null) {
                    customers = new CustomerMapper().map(result);
                    customerMap.put(customerId, customers);
                }
                Order order = new OrderMapper().map(result);
                if (order.getId() != 0) {
                    customers.getOrders().add(order);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return customers;
    }


    @Override
    public Boolean exists(Integer integer) {
        return null;
    }

    @Override
    public Boolean save(Customer customer) {
        return null;
    }

    @Override
    public Integer update(Customer customer) {
        return null;
    }


    @Override
    public Customer findById(int id) {
        return null;
    }
}
