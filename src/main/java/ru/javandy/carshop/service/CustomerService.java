package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO findByCustomerId(int id);
    CustomerDTO updateCustomerId(CustomerDTO customerDTO, int id);
    CustomerDTO findByCar(CarDTO carDTO);
}