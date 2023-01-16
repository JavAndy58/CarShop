package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.dto.CustomerDto;
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


    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDto saveCustomer(CustomerDto customerDTO) {
        return customerMapper.toDTO(customerRepository.save(customerMapper.toEntity(customerDTO)));
    }

    public CustomerDto findByCustomerId(int id) {
        return customerMapper.toDTO(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    public CustomerDto updateCustomerId(CustomerDto newCustomerDto, int id) {
        CarDto carDTO = null;
        if (!newCustomerDto.getCars().isEmpty()) {
            carDTO = newCustomerDto.getCars().get(newCustomerDto.getCars().size() - 1);
        }
        CarDto finalCarDto = carDTO;
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomerDto.getName());
                    customer.setPhoneNumber(newCustomerDto.getPhoneNumber());
                     if (customer.getCars().size() != newCustomerDto.getCars().size()) {
                         customer.addCar(carMapper.toEntity(finalCarDto));
                     }
                    return customerMapper.toDTO(customerRepository.save(customer));
                }).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public CustomerDto findByCar(CarDto carDTO) {
        return customerMapper.toDTO(customerRepository.findByCars(carMapper.toEntity(carDTO)));
    }
}
