package mapper;



import model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper extends Mapper<Order> {

    @Override
    public Order map(ResultSet result) throws SQLException {
        Order order = new Order();
        order.setId(result.getInt("orderNumber"));
        order.setOrderDate(result.getDate("orderDate").toLocalDate());
        order.setRequiredDate(result.getDate("requiredDate").toLocalDate());
        order.setShippedDate(result.getDate("shippedDate").toLocalDate());
        order.setStatus(result.getString("status"));
        order.setComments(result.getString("comments"));
        order.setCustomerNumber(result.getInt("customerNumber"));
        return order;
    }
}
