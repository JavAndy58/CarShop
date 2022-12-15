package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.exeption.CarNotFoundException;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.service.CarService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars()
                .stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/car/{id}")
    public CarDTO getCarId(@PathVariable int id) {
        Car car = carService.findByCarId(id);
        return carMapper.toDTO(car);
    }

    @PostMapping("/car")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
        Car car = carMapper.toEntity(carDTO);
        return carMapper.toDTO(carService.saveCar(car));
    }

    @PutMapping("/car/{id}")
    CarDTO updateCar(@RequestBody CarDTO carDTO, @PathVariable int id) {
        Car car = carMapper.toEntity(carDTO);
        return carMapper.toDTO(carService.updateCarId(car, id));
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
