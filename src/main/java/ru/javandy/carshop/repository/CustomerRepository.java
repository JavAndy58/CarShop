package ru.javandy.carshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javandy.carshop.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
