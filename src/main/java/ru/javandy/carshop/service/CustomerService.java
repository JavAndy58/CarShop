package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto saveCustomer(CustomerDto customerDTO);
    CustomerDto findByCustomerId(int id);
    CustomerDto updateCustomerId(CustomerDto customerDTO, int id);
    CustomerDto findByCar(CarDto carDTO);
}