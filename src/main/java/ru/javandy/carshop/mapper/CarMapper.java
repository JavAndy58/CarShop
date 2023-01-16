package ru.javandy.carshop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.model.Car;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {
    CarDto toDto(Car car);
    Car toEntity(CarDto carDto);
}
