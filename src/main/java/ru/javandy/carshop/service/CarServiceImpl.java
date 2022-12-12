package ru.javandy.carshop.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.exeption.CarNotFoundException;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.repository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    public List<CarDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CarDTO saveCar(CarDTO carDTO) {
        Car car = carRepository.save(toEntity(carDTO));
        return toDTO(car);
    }

    public CarDTO findByCarId(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        return toDTO(car);
    }

    public CarDTO updateCarId(CarDTO newCarDTO, int id) {
        Car newCar = toEntity(newCarDTO);
        Car updateCar = carRepository.findById(id)
                .map(car -> {
                    car.setName(newCar.getName());
                    car.setVinCode(newCar.getVinCode());
                    return carRepository.save(car);
                }).orElseThrow(() -> new CarNotFoundException(id));
        return toDTO(updateCar);
    }

    public void deleteByCarId(int id) {
        carRepository.deleteById(id);
    }

    public boolean existsByCarId(int id) {
        return  carRepository.existsById(id);
    }

    private CarDTO toDTO(Car car) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(car, CarDTO.class);
    }
    private Car toEntity(CarDTO carDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(carDTO, Car.class);
    }

}
