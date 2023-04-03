package repository.impl;

import mapper.Mapper;
import model.entity.Customer;
import model.entity.Office;
import model.entity.Order;

import java.util.List;

public class OrderRepository extends BaseRepository<Order, Integer>{

    public OrderRepository(Mapper<Order> mapper) {
        super(mapper);
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findById(Integer integer) {
        return null;
    }

    @Override
    public Boolean exists(Integer integer) {
        return null;
    }

    @Override
    public Boolean save(Order order) {
        return null;
    }

    @Override
    public Integer update(Order order) {
        return null;
    }
}
