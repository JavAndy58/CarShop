package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.exeption.CarNotFoundException;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.CarRepository;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CustomerService customerService;
    private final OrderService orderService;

    public CarServiceImpl(CarRepository carRepository, CustomerService customerService, OrderService orderService) {
        this.carRepository = carRepository;
        this.customerService = customerService;
        this.orderService = orderService;
    }

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
        Customer customerCarDel = customerService.findByCars(carDel);
        List<Order> ordersCarDel = orderService.getAllOrdersCar(customerCarDel, carDel);
        ordersCarDel.forEach(customer -> customer.setCar(null));
        orderService.saveOrders(ordersCarDel);
        customerCarDel.removeCar(carDel);
        customerService.saveCustomer(customerCarDel);
    }

    public boolean existsByCarId(int id) {
        return  carRepository.existsById(id);
    }
}
