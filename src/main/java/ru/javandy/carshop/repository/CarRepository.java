package ru.javandy.carshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javandy.carshop.model.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
}
