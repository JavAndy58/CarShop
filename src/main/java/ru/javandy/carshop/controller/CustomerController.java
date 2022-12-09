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

//    @GetMapping("/customers")
//    public List<CustomerDTO> getAllCustomers() {
//        return customerService.findAll()
//                .stream()
//                .map(customerMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping("/customer")
//    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
//        Customer customer = CustomerMapper.INSTANCE.toCustomer(customerDTO);
//
//        return customerMapper.toDTO(customerService.save(customer));
//    }
//
//    @GetMapping("/customer/{id}")
//    public CustomerDTO getCustomerById(@PathVariable int id) {
//        Customer customer = customerService.findById(id)
//                .orElseThrow(() -> new CustomerNotFoundException(id));
//        return CustomerMapper.INSTANCE.toDTO(customer);
//    }
//
//    @PutMapping("/customer/{id}")
//    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable int id) {
//        return customerService.findById(id)
//                .map(customer -> {
//                    customer.setName(newCustomer.getName());
//                    customer.setPhoneNumber(newCustomer.getPhoneNumber());
//                    return customerService.save(customer);
//                }).orElseThrow(() -> new CustomerNotFoundException(id));
//    }

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
