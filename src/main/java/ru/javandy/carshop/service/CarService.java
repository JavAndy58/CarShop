package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        List<Car> rsl = new ArrayList<>();
        carRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void delete(Car car) {
        carRepository.delete(car);
    }

}
