package ru.javandy.carshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javandy.carshop.model.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
