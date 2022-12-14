package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<Car> findAll() {
        List<Car> rsl = new ArrayList<>();
        carRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    @Override
    public List<Car> findByCustomer(Customer customer) {
        List<Car> rsl = new ArrayList<>();
        carRepository.findByCustomer(customer).forEach(rsl::add);
        return rsl;
    }

    @Override
    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> saveAll(List<Car> cars) {
        return (List<Car>) carRepository.saveAll(cars);
    }

    @Override
    public void deleteById(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return carRepository.existsById(id);
    }

}
