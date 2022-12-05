package ru.javandy.carshop.service;

import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();
    List<Car> findByCustomer(Customer customer);
    Car save(Car car);
    List<Car> saveAll(List<Car> cars);
    Optional<Car> findById(int id);
    void deleteById(int id);
    boolean existsById(int id);
}
