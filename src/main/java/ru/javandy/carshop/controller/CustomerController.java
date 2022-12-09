package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.CustomerDTO;
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
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customer")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerId(@PathVariable int id) {
        return customerService.findByCustomerId(id);
    }

    @PutMapping("/customer/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable int id) {
        return customerService.updateCustomerId(customerDTO, id);
    }
}
