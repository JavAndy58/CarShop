package ru.javandy.carshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javandy.carshop.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
