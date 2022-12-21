package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.exeption.CarNotFoundException;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.model.Car;
//import ru.javandy.carshop.repository.CarRepository;
import ru.javandy.carshop.service.CarService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public CarDTO getCarId(@PathVariable int id) {
        return carService.findByCarId(id);
    }

    @PostMapping("/car")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
        return carService.saveCar(carDTO);
    }

    @PutMapping("/car/{id}")
    CarDTO updateCar(@RequestBody CarDTO carDTO, @PathVariable int id) {
        return carService.updateCarId(carDTO, id);
    }

    @DeleteMapping("/car/{id}")
    String deleteCar(@PathVariable int id) {
        if (!carService.existsByCarId(id)) {
            throw new CarNotFoundException(id);
        }
        carService.deleteByCarId(id);
        return "Car with id " + id + " has been delete success.";
    }
}
