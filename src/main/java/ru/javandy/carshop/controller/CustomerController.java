package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.mapper.CustomerMapper;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerId(@PathVariable int id) {
        Customer customer = customerService.findByCustomerId(id);
        return customerMapper.toDTO(customer);
    }

    @PostMapping("/customer")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        return customerMapper.toDTO(customerService.saveCustomer(customer));
    }

    @PutMapping("/customer/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable int id) {
        Customer customer = customerMapper.toEntity(customerDTO);
        return customerMapper.toDTO(customerService.updateCustomerId(customer, id));
    }
}
