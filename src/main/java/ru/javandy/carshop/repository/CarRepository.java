package ru.javandy.carshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javandy.carshop.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
