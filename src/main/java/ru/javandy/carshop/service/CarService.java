package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> getAllCars();
    CarDto saveCar(CarDto carDto);
    CarDto findByCarId(int id);
    CarDto updateCarId(CarDto carDto, int id);
    void deleteByCarId(int id);
    boolean existsByCarId(int id);
}
