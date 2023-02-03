package ru.javandy.carshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.dto.CustomerDto;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.mapper.CustomerMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.repository.CustomerRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { CustomerServiceImpl.class, CustomerMapper.class, CarMapper.class})
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerMapper customerMapper;

    @MockBean
    private CarMapper carMapper;

    @Test
    void getAllCustomers() {
        Car car1 = new Car(1, "Focus 2", "XXEERTY525SA626");
        Customer customer1 = new Customer(1, "User1", "+79998885252", Collections.singletonList(car1));
        Customer customer2 = new Customer(2, "User2", "+79997776363", null);

        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDto1);
        CustomerDto customerDto2 = new CustomerDto("User2", "+79997776363");

        when(customerMapper.toDto(customer1)).thenReturn(customerDto1);
        when(customerMapper.toDto(customer2)).thenReturn(customerDto2);
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        customerService.getAllCustomers();
        verify(customerRepository, times(1)).findAll();
        verify(customerMapper, times(1)).toDto(customer1);
        verify(customerMapper, times(1)).toDto(customer2);
    }

    @Test
    void saveCustomer() {
        Car car1 = new Car(1, "Focus 2", "XXEERTY525SA626");
        Customer customer1 = new Customer(1, "User1", "+79998885252", Collections.singletonList(car1));
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDto1);

        when(customerMapper.toEntity(customerDto1)).thenReturn(customer1);
        when(customerRepository.save(customer1)).thenReturn(customer1);
        when(customerMapper.toDto(customer1)).thenReturn(customerDto1);

        customerService.saveCustomer(customerDto1);
        verify(customerRepository, times(1)).save(customer1);
        verify(customerMapper, times(1)).toEntity(customerDto1);
        verify(customerMapper, times(1)).toDto(customer1);
    }

    @Test
    void findByCustomerId() {
        int testId = 1;
        Car car1 = new Car(1, "Focus 2", "XXEERTY525SA626");
        Customer customer1 = new Customer(1, "User1", "+79998885252", Collections.singletonList(car1));
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDto1);
        Optional<Customer> optionalCustomer = Optional.of(customer1);

        when(customerRepository.findById(testId)).thenReturn(optionalCustomer);
        when(customerMapper.toDto(customer1)).thenReturn(customerDto1);

        customerService.findByCustomerId(testId);
        verify(customerRepository, times(1)).findById(testId);
        verify(customerMapper, times(1)).toDto(customer1);
    }

    @Test
    void updateCustomerId() {
        int testId = 1;
        Car car1 = new Car(testId, "Focus 2", "XXEERTY525SA626");
        Customer existing = new Customer(testId, null, null, Collections.singletonList(car1));

        Customer updateCustomer = new Customer(testId, "User1", "+79998885252", Collections.singletonList(car1));
        CarDto updateCarDto = new CarDto(testId, "Focus 2", "XXEERTY525SA626");
        CustomerDto updateCustomerDto = new CustomerDto(testId, "User1", "+79998885252", Collections.singletonList(updateCarDto));

        CarDto testCarDto = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto testCustomerDto = new CustomerDto("User1", "+79998885252");
        testCustomerDto.addCarDTO(testCarDto);

        when(customerRepository.findById(testId)).thenReturn(Optional.of(existing));
        when(customerMapper.toDto(updateCustomer)).thenReturn(updateCustomerDto);
        when(customerRepository.save(updateCustomer)).thenReturn(updateCustomer);

        customerService.updateCustomerId(testCustomerDto, testId);
        verify(customerRepository, times(1)).findById(testId);
        verify(customerRepository, times(1)).save(updateCustomer);
    }

    @Test
    void findByCar() {
        int testId = 1;
        Car testCar = new Car(testId, "Focus 2", "XXEERTY525SA626");
        Customer testCustomer = new Customer(testId, "User1", "+79998885252", Collections.singletonList(testCar));
        CarDto testCarDto = new CarDto(testId, "Focus 2", "XXEERTY525SA626");
        CustomerDto testCustomerDto = new CustomerDto("User1", "+79998885252");
        testCustomerDto.addCarDTO(testCarDto);

        when(customerRepository.findByCars(testCar)).thenReturn(testCustomer);
        when(carMapper.toEntity(testCarDto)).thenReturn(testCar);
        when(customerMapper.toDto(testCustomer)).thenReturn(testCustomerDto);

        customerService.findByCar(testCarDto);
        verify(customerRepository, times(1)).findByCars(testCar);
    }
}