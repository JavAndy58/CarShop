package ru.javandy.carshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByCustomer(Customer customer);
}
