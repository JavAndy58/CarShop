package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.CustomerDto;
import ru.javandy.carshop.service.CustomerService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerId(@PathVariable int id) {
        return customerService.findByCustomerId(id);
    }

    @PutMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDTO, @PathVariable int id) {
        return customerService.updateCustomerId(customerDTO, id);
    }
}
