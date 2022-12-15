package ru.javandy.carshop.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.model.Customer;

import java.util.List;

@Mapper(componentModel = "spring", uses = CarMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);


//    List<Customer> toCustomerList(List<CustomerDTO> customerDTOS);
//    @InheritInverseConfiguration
//    List<CustomerDTO> toCustomerDTOList(List<Customer> customers);
}
