package ru.javandy.carshop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.model.Car;

@Mapper(componentModel = "spring", uses = CustomerMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {
    CarDTO toDTO(Car car);
    Car toEntity(CarDTO carDTO);
}
