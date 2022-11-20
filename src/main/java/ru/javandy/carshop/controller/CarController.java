package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.Exeption.CarNotFoundException;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.service.CarService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
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

    @DeleteMapping("/car/{id}")
    String deleteCar(@PathVariable int id) {
        if (!carService.existsById(id)) {
            throw new CarNotFoundException(id);
        }
        carService.deleteById(id);
        return "Car with id " + id + " has been deleted success.";
    }

}
