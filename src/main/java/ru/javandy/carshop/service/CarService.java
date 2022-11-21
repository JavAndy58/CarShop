package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();
    Car save(Car car);
    Optional<Car> findById(int id);
    void deleteById(int id);
    boolean existsById(int id);
}
