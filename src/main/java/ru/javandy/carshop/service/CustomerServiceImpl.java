package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.exeption.CustomerNotFoundException;
import ru.javandy.carshop.mapper.CustomerMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return customerMapper.toDTO(customerRepository.save(customerMapper.toEntity(customerDTO)));
    }

    public CustomerDTO findByCustomerId(int id) {
        return customerMapper.toDTO(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    public CustomerDTO updateCustomerId(CustomerDTO newCustomerDTO, int id) {

        Car car = null;
        if (!newCustomer.getCars().isEmpty()) {
            car = newCustomer.getCars().get(newCustomer.getCars().size() - 1);
        }
        Car finalCar = car;
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setPhoneNumber(newCustomer.getPhoneNumber());

                     if (customer.getCars().size() != newCustomer.getCars().size()) {
                         customer.addCar(finalCar);
                     }

                    return customerRepository.save(customer);
                }).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer findByCars(Car car) {
        return customerRepository.findByCars(car);
    }
}
