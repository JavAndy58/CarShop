package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Order save(Order order);
    Optional<Order> findById(int id);
    boolean existsById(int id);
}
