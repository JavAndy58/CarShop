package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.service.CarService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/car/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto getCarId(@PathVariable int id) {
        return carService.findByCarId(id);
    }

    @PostMapping("/car")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@RequestBody CarDto carDTO) {
        return carService.saveCar(carDTO);
    }

    @PutMapping("/car/{id}")
    @ResponseStatus(HttpStatus.OK)
    CarDto updateCar(@RequestBody CarDto carDTO, @PathVariable int id) {
        return carService.updateCarId(carDTO, id);
    }

    @DeleteMapping("/car/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable int id) {
        carService.deleteByCarId(id);
    }
}
