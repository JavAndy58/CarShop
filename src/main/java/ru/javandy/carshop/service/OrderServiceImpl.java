package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        List<Order> rsl = new ArrayList<>();
        orderRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    @Override
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public boolean existsById(int id) {
        return orderRepository.existsById(id);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }
}
