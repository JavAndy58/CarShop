package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.exeption.CarNotFoundException;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.CarRepository;
import ru.javandy.carshop.repository.CustomerRepository;
import ru.javandy.carshop.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car findByCarId(int id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    public Car updateCarId(Car newCar, int id) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setName(newCar.getName());
                    car.setVinCode(newCar.getVinCode());
                    return carRepository.save(car);
                }).orElseThrow(() -> new CarNotFoundException(id));
    }

    public void deleteByCarId(int id) {

        Car carDel = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        Customer customerCarDel = customerRepository.findByCars(carDel);
        customerCarDel.removeCar(carDel);
        customerRepository.save(customerCarDel);












//        List<Order> collect = orderRepository.findByCustomerAndCar(customerCarDel, carDel)
//                .stream()
//                .map(order -> order.setCar(null))
//                .collect(Collectors.toList());
//

    }

    public boolean existsByCarId(int id) {
        return  carRepository.existsById(id);
    }
}
