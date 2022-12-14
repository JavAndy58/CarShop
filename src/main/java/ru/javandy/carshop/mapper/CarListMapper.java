package ru.javandy.carshop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.model.Car;

import java.util.List;

@Mapper(componentModel = "spring", uses = CarMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarListMapper {
    List<Car> toEntityList(List<CarDTO> carDTOS);
    List<CarDTO> toDTOList(List<Car> cars);
}
