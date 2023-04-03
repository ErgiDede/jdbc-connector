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

        // Fetch orders for this customer
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement("SELECT o.* FROM orders o JOIN customers c ON o.customerNumber = c.customerNumber WHERE c.customerNumber = ?")) {
            statement.setInt(1, customer.getId());
            ResultSet orderResult = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (orderResult.next()) {
                Order order = new Order();
                order.setId(orderResult.getInt("orderNumber"));
                order.setOrderDate(orderResult.getDate("orderDate").toLocalDate());
                order.setRequiredDate(orderResult.getDate("requiredDate").toLocalDate());
                if (orderResult.getDate("shippedDate") != null) {
                    order.setShippedDate(orderResult.getDate("shippedDate").toLocalDate());
                }
                order.setStatus(orderResult.getString("status"));
                order.setComments(orderResult.getString("comments"));
                orders.add(order);
            }
            customer.setOrders(orders);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return customer;
    }
}


