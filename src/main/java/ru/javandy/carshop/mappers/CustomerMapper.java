package ru.javandy.carshop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.model.Customer;

@Mapper
@Service
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO toDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
}
