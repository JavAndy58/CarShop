package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order saveOrder(Order order);
    Order findByOrderId(int id);
    Order updateOrderId(Order order, int id);
    boolean existsByOrderId(int id);
    void deleteByOrderId(int id);
}
