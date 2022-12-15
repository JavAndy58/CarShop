package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Car;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car saveCar(Car car);
    Car findByCarId(int id);
    Car updateCarId(Car car, int id);
    void deleteByCarId(int id);
    boolean existsByCarId(int id);
}
