package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.exeption.CustomerNotFoundException;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findByCustomerId(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer updateCustomerId(Customer newCustomer, int id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setPhoneNumber(newCustomer.getPhoneNumber());
                    customer.setCars(newCustomer.getCars());
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
