package ru.javandy.carshop.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.exeption.CustomerNotFoundException;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(toEntity(customerDTO));
        return toDTO(customer);
    }

    public CustomerDTO findByCustomerId(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return toDTO(customer);
    }

    public CustomerDTO updateCustomerId(CustomerDTO newCustomerDTO, int id) {
        Customer newCustomer = toEntity(newCustomerDTO);
        Customer updateCustomer = customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setPhoneNumber(newCustomer.getPhoneNumber());
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new CustomerNotFoundException(id));
        return toDTO(updateCustomer);
    }

    private CustomerDTO toDTO(Customer customer) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(customer, CustomerDTO.class);
    }
    private Customer toEntity(CustomerDTO customerDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(customerDTO, Customer.class);
    }
}
