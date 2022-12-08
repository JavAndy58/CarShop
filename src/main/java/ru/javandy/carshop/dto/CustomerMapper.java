package ru.javandy.carshop.dto;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.model.Customer;

import java.util.List;

@Service
public interface CustomerMapper {
    default CustomerDTO customerToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

    default Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .phoneNumber(customerDTO.getName())
                .build();
    }

    default List<CustomerDTO> customerToCustomerDTOs(List<Customer> customers) {
//        return customers.map(this::customerToCustomerDTO);
        return customers.forEach(this::customerToCustomerDTO);
    }
}
