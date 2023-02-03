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
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerMapper.toDto(customerRepository.save(customerMapper.toEntity(customerDto)));
    }

    public CustomerDto findByCustomerId(int id) {
        return customerMapper.toDto(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    public CustomerDto updateCustomerId(CustomerDto newCustomerDto, int id) {
        CarDto carDto = null;
        if (!newCustomerDto.getCars().isEmpty()) {
            carDto = newCustomerDto.getCars().get(newCustomerDto.getCars().size() - 1);
        }
        CarDto finalCarDto = carDto;
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomerDto.getName());
                    customer.setPhoneNumber(newCustomerDto.getPhoneNumber());
                     if (customer.getCars().size() != newCustomerDto.getCars().size()) {
                         customer.addCar(carMapper.toEntity(finalCarDto));
                     }
                    return customerMapper.toDto(customerRepository.save(customer));
                }).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public CustomerDto findByCar(CarDto carDto) {
        return customerMapper.toDto(customerRepository.findByCars(carMapper.toEntity(carDto)));
    }
}
