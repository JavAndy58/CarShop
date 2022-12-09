package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO findByCustomerId(int id);
    CustomerDTO updateCustomerId(CustomerDTO customerDTO, int id);
}