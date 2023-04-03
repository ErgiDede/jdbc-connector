package repository.impl;

import mapper.CustomerMapper;
import mapper.OrderMapper;
import model.entity.Customer;
import model.entity.Order;
import util.JdbcConnection;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository extends BaseRepository<Customer, Integer>{

    public CustomerRepository() {
        super(new CustomerMapper());
    }

    OrderRepository orderRepository = new OrderRepository();
    @Override
    public List<Customer> findAll() {
        Map<Integer, Customer> customerMap = new HashMap<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_CUSTOMERS)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int customerId = result.getInt("customerNumber");
                if (!customerMap.containsKey(customerId)) {
                    Customer customer = getMapper().map(result);
                    customer.setOrders(new ArrayList<>());
                    customerMap.put(customerId, customer);
                }
                Order order = OrderMapper.buildOrderFromResultSet(result);
                customerMap.get(customerId).getOrders().add(order);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>(customerMap.values());
    }



   /* @Override
    public Customer findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_CUSTOMER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Customer customer = null;
            while (result.next()) {
                if (customer == null) {
                    customer = new Customer();
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
                    customer.setOrders(new ArrayList<>());
                }
                Order order = new Order();
                order.setId(result.getInt("order_id"));
                order.setOrderDate(result.getDate("orderDate").toLocalDate());
                order.setRequiredDate(result.getDate("requiredDate").toLocalDate());
                order.setShippedDate(result.getDate("shippedDate").toLocalDate());
                order.setStatus(result.getString("status"));
                order.setComments(result.getString("comments"));
                order.setCustomerNumber(result.getInt("order_customer_number"));
                customer.getOrders().add(order);
            }
            return customer;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }*/

    @Override
    public Customer findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_CUSTOMER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Customer customer = null;
            while (result.next()) {
                if (customer == null) {
                    customer = getMapper().map(result);
                    customer.setOrders(new ArrayList<>());
                }
                Order order = OrderMapper.buildOrderFromResultSet(result);
                customer.getOrders().add(order);
            }
            return customer;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    @Override
    public Boolean exists(Integer integer) {
        return null;
    }

    @Override
    public Boolean save(Customer customer) {
        try (Connection connection = JdbcConnection.connect()) {
            if (customer != null) {
                // insert new customer
                PreparedStatement statement = connection.prepareStatement(Queries.INSERT_CUSTOMER);
                statement.setInt(1,customer.getId());
                statement.setString(2, customer.getCustomerName());
                statement.setString(3, customer.getContactLastName());
                statement.setString(4, customer.getContactFirstName());
                statement.setString(5, customer.getPhone());
                statement.setString(6, customer.getAddressLine1());
                statement.setString(7, customer.getAddressLine2());
                statement.setString(8, customer.getCity());
                statement.setString(9, customer.getState());
                statement.setString(10, customer.getPostalCode());
                statement.setString(11, customer.getCountry());
                statement.setInt(12, customer.getSalesRepEmployeeNumber());
                statement.setDouble(13, customer.getCreditLimit());
                statement.executeUpdate();
            }

            // insert or update orders
            List<Order> orders = customer.getOrders();
            for (Order order : orders) {
                PreparedStatement statement = connection.prepareStatement(Queries.FIND_ORDER_BY_ID);
                statement.setInt(1, order.getId());
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    // update existing order
                    PreparedStatement updateStatement = connection.prepareStatement(Queries.UPDATE_ORDER);
                    updateStatement.setDate(1, Date.valueOf(order.getOrderDate()));
                    updateStatement.setDate(2, Date.valueOf(order.getRequiredDate()));
                    updateStatement.setDate(3, order.getShippedDate() != null ? Date.valueOf(order.getShippedDate()) : null);
                    updateStatement.setString(4, order.getStatus());
                    updateStatement.setString(5, order.getComments());
                    updateStatement.setInt(6, order.getCustomerNumber());
                    updateStatement.setInt(7, order.getId());
                    updateStatement.executeUpdate();
                } else {
                    // insert new order
                    PreparedStatement insertStatement = connection.prepareStatement(Queries.INSERT_ORDER);
                    insertStatement.setInt(1, order.getId());
                    insertStatement.setDate(2, Date.valueOf(order.getOrderDate()));
                    insertStatement.setDate(3, Date.valueOf(order.getRequiredDate()));
                    insertStatement.setDate(4, order.getShippedDate() != null ? Date.valueOf(order.getShippedDate()) : null);
                    insertStatement.setString(5, order.getStatus());
                    insertStatement.setString(6, order.getComments());
                    insertStatement.setInt(7, order.getCustomerNumber());
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }


    @Override
    public Integer update(Customer customer) {
        try (Connection connection = JdbcConnection.connect()) {

            if (customer != null) {
                // update customer
                PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_CUSTOMER);
                statement.setString(1, customer.getCustomerName());
                statement.setString(2, customer.getContactLastName());
                statement.setString(3, customer.getContactFirstName());
                statement.setString(4, customer.getPhone());
                statement.setString(5, customer.getAddressLine1());
                statement.setString(6, customer.getAddressLine2());
                statement.setString(7, customer.getCity());
                statement.setString(8, customer.getState());
                statement.setString(9, customer.getPostalCode());
                statement.setString(10, customer.getCountry());
                statement.setInt(11, customer.getSalesRepEmployeeNumber());
                statement.setDouble(12, customer.getCreditLimit());
                statement.setInt(13, customer.getId());
                int rowsAffected = statement.executeUpdate();

                // update orders
                List<Order> orders = customer.getOrders();
                for (Order order : orders) {
                    PreparedStatement updateStatement = connection.prepareStatement(Queries.UPDATE_ORDER);
                    updateStatement.setDate(1, Date.valueOf(order.getOrderDate()));
                    updateStatement.setDate(2, Date.valueOf(order.getRequiredDate()));
                    updateStatement.setDate(3, order.getShippedDate() != null ? Date.valueOf(order.getShippedDate()) : null);
                    updateStatement.setString(4, order.getStatus());
                    updateStatement.setString(5, order.getComments());
                    updateStatement.setInt(6, order.getCustomerNumber());
                    updateStatement.setInt(7, order.getId());
                    updateStatement.executeUpdate();
                }

                return rowsAffected;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }


}
