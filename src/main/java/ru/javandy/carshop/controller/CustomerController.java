package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.exeption.CustomerNotFoundException;
import ru.javandy.carshop.mappers.CustomerMapper;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/customer")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.toCustomer(customerDTO);

        return customerMapper.toDTO(customerService.save(customer));
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable int id) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return CustomerMapper.INSTANCE.toDTO(customer);
    }



    @PutMapping("/customer/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable int id) {
        return customerService.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setPhoneNumber(newCustomer.getPhoneNumber());
                    return customerService.save(customer);
                }).orElseThrow(() -> new CustomerNotFoundException(id));
    }

}
