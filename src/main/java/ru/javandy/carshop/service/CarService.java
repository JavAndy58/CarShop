package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CarDTO;
import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();
    CarDTO saveCar(CarDTO carDto);
    CarDTO findByCarId(int id);
    CarDTO updateCarId(CarDTO carDto, int id);
    void deleteByCarId(int id);
    boolean existsByCarId(int id);
}
