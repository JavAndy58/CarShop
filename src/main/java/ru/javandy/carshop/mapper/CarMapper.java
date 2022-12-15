package ru.javandy.carshop.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.model.Car;

import java.util.List;

@Mapper(componentModel = "spring", uses = CustomerMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {

    CarDTO toDTO(Car car);
    Car toEntity(CarDTO carDTO);


//    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
//    Car toEntity(CarDTO carDTO);
//    List<Car> toCarList(List<CarDTO> carDTOS);
//    @InheritConfiguration
//    List<CarDTO> toCarDTOsList(List<Car> cars);
}
