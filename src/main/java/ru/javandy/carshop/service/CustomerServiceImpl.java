package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.dto.CustomerMapper;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findAll() {
//        List<Customer> rsl = new ArrayList<>();
//        customerRepository.findAll().forEach(rsl::add);
//        return rsl;
        List<CustomerDTO> rsl = new ArrayList<>();
        customerRepository.findAll().
        return
    }

    @Override
    public CustomerDTO findById(int id) throws Exception {
        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(Exception::new);
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();
        Customer saveCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(saveCustomer);
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
