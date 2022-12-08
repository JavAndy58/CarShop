package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> findAll();
    CustomerDTO save(CustomerDTO customerDTO);
    List<Customer> saveAll(List<Customer> customers);
    CustomerDTO findById(int id);
    boolean existsById(int id);
}
