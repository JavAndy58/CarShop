package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CarDTO;
import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();
    CarDTO saveCar(CarDTO carDTO);
    CarDTO findByCarId(int id);
    CarDTO updateCarId(CarDTO carDTO, int id);
    void deleteByCarId(int id);
    boolean existsByCarId(int id);

}
