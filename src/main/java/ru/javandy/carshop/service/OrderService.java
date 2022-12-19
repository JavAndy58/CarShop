package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Order;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order saveOrder(Order order);
    List<Order> saveOrders(List<Order> orders);
    Order findByOrderId(int id);
    Order updateOrderId(Order order, int id);
    boolean existsByOrderId(int id);
    void deleteByOrderId(int id);
    List<Order> getAllOrderCustomerAndCar(Customer customer, Car car);
}
