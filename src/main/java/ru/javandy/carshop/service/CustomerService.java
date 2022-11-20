package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Customer save(Customer customer);
    Optional<Customer> findById(int id);
    boolean existsById(int id);
}
