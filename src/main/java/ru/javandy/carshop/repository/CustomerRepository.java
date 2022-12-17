package ru.javandy.carshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByCars(Car car);
}
