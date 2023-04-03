package mapper;

import model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderMapper extends Mapper<Order> {

    @Override
    public Order map(ResultSet result) throws SQLException {
        Order order = new Order();
        order.setId(result.getInt("orderNumber"));
        order.setOrderDate(result.getDate("orderDate").toLocalDate());
        order.setRequiredDate(result.getDate("requiredDate").toLocalDate());
        order.setShippedDate(getLocalDateOrNull(result, "shippedDate"));
        order.setStatus(result.getString("status"));
        order.setComments(result.getString("comments"));
        order.setCustomerNumber(result.getInt("customerNumber"));
        return order;
    }

    private static LocalDate getLocalDateOrNull(ResultSet result, String columnName) throws SQLException {
        java.sql.Date sqlDate = result.getDate(columnName);
        return sqlDate != null ? sqlDate.toLocalDate() : null;
    }

    public static Order buildOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("orderNumber"));
        order.setOrderDate(resultSet.getDate("orderDate") != null ? resultSet.getDate("orderDate").toLocalDate() : null);
        order.setRequiredDate(resultSet.getDate("requiredDate") != null ? resultSet.getDate("requiredDate").toLocalDate() : null);
        order.setShippedDate(resultSet.getDate("shippedDate") != null ? resultSet.getDate("shippedDate").toLocalDate() : null);
        order.setStatus(resultSet.getString("status"));
        order.setComments(resultSet.getString("comments"));
        order.setCustomerNumber(resultSet.getInt("customerNumber"));
        return order;

    }

}
