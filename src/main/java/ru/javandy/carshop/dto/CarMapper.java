package ru.javandy.carshop.dto;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.model.Car;

@Service
@Mapper
public interface CarMapper {
    CarDTO carToCarDTO(Car car);
    Car carDTOToCar(CarDTO carDTO);
}
