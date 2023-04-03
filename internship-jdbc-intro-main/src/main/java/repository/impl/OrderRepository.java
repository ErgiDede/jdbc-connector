package repository.impl;

import mapper.Mapper;
import mapper.OrderMapper;
import model.entity.Customer;
import model.entity.Office;
import model.entity.Order;
import util.JdbcConnection;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends BaseRepository<Order, Integer> {

    public OrderRepository() {
        super(new OrderMapper());
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_ORDERS)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = getMapper().map(result);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ORDER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.CHECK_IF_ORDER_EXISTS)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean save(Order order) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (orderDate, requiredDate, shippedDate, status, comments, customerNumber) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setDate(1, Date.valueOf(order.getOrderDate()));
            statement.setDate(2, Date.valueOf(order.getRequiredDate()));
            statement.setDate(3, order.getShippedDate() != null ? Date.valueOf(order.getShippedDate()) : null);
            statement.setString(4, order.getStatus());
            statement.setString(5, order.getComments());
            statement.setInt(6, order.getCustomerNumber());
            int numRowsInserted = statement.executeUpdate();
            System.out.println(numRowsInserted + " row(s) inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return true;
    }

    @Override
    public Integer update(Order order) {
        int numRecordsUpdated = 0;
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement("UPDATE orders SET orderDate = ?, requiredDate = ?, shippedDate = ?, status = ?, comments = ?, customerNumber = ? WHERE orderNumber = ?")) {
            statement.setDate(1, Date.valueOf(order.getOrderDate()));
            statement.setDate(2, Date.valueOf(order.getRequiredDate()));
            statement.setDate(3, order.getShippedDate() != null ? Date.valueOf(order.getShippedDate()) : null);
            statement.setString(4, order.getStatus());
            statement.setString(5, order.getComments());
            statement.setInt(6, order.getCustomerNumber());
            statement.setInt(7, order.getId());
            numRecordsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return numRecordsUpdated;
    }

    public List<Order> findAllByCusmoerId(Integer customerId) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_ORDERS_BY_CUSTOMER_ID)) {
            statement.setInt(1,customerId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = getMapper().map(result);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return orders;
    }
}

