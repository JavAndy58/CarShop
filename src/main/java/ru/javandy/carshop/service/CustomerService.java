package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer findByCustomerId(int id);
    Customer updateCustomerId(Customer customer, int id);
//    Customer findByCars(Car car);
}