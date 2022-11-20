package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.Exeption.CustomerNotFoundException;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.service.CustomerService;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable int id) {
        return customerService.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
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
