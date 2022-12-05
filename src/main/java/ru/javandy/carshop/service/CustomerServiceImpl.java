package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> rsl = new ArrayList<>();
        customerRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        return (List<Customer>) customerRepository.saveAll(customers);
    }

    @Override
    public boolean existsById(int id) {
        return customerRepository.existsById(id);
    }
}
