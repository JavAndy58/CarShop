package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.exeption.CustomerNotFoundException;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.mapper.CustomerMapper;
import ru.javandy.carshop.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CarMapper carMapper;


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
        CarDTO carDTO = null;
        if (!newCustomerDTO.getCars().isEmpty()) {
            carDTO = newCustomerDTO.getCars().get(newCustomerDTO.getCars().size() - 1);
        }
        CarDTO finalCarDTO = carDTO;
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomerDTO.getName());
                    customer.setPhoneNumber(newCustomerDTO.getPhoneNumber());
                     if (customer.getCars().size() != newCustomerDTO.getCars().size()) {
                         customer.addCar(carMapper.toEntity(finalCarDTO));
                     }
                    return customerMapper.toDTO(customerRepository.save(customer));
                }).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public CustomerDTO findByCar(CarDTO carDTO) {
        return customerMapper.toDTO(customerRepository.findByCars(carMapper.toEntity(carDTO)));
    }
}
