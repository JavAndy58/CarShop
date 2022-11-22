package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.Exeption.CarNotFoundException;
import ru.javandy.carshop.Exeption.CustomerNotFoundException;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.service.CarService;
import ru.javandy.carshop.service.CustomerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;
    private final CustomerService customerService;

    public CarController(CarService carService, CustomerService customerService) {
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    @PostMapping("/car")
    public Car createCar(@RequestBody Car car) {
        return carService.save(car);
    }

    @GetMapping("/car/{id}")
    Car getCarById(@PathVariable int id) {
        return carService.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }
    @GetMapping("/car_customer/{id}")
    public List<Car> getCarByCustomer(@PathVariable int id) {
        return carService.findByCustomer(customerService.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    @DeleteMapping("/car/{id}")
    String deleteCar(@PathVariable int id) {
        if (!carService.existsById(id)) {
            throw new CarNotFoundException(id);
        }
        carService.deleteById(id);
        return "Car with id " + id + " has been deleted success.";
    }

}
