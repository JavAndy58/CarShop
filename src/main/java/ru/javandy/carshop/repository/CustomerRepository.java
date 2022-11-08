package ru.javandy.carshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javandy.carshop.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
