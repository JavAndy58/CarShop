package ru.javandy.carshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javandy.carshop.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
