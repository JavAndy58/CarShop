package ru.javandy.carshop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.CustomerDto;
import ru.javandy.carshop.model.Customer;

@Mapper(componentModel = "spring", uses = CarMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
}
