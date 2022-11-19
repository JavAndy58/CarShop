package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer save(Customer customer);
}
