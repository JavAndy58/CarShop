package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.exeption.CarNotFoundException;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.repository.CarRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;


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
                    car.setCustomer(newCar.getCustomer());
                    return carRepository.save(car);
                }).orElseThrow(() -> new CarNotFoundException(id));
    }

    public void deleteByCarId(int id) {
        carRepository.deleteById(id);
    }

    public boolean existsByCarId(int id) {
        return  carRepository.existsById(id);
    }


//    public List<CarDTO> getAllCars() {
//        return carRepository.findAll()
//                .stream()
//                .map(carMapper::toDTO)
//                .collect(Collectors.toList());
//    }

//    public Car saveCar(Car car) {
//        Car car = carRepository.save(carMapper.toEntity(car));
//        return carMapper.toDTO(car);
//    }

//    public CarDTO findByCarId(int id) {
//        Car car = carRepository.findById(id)
//                .orElseThrow(() -> new CarNotFoundException(id));
//        return carMapper.toDTO(car);
//    }

//    public CarDTO updateCarId(CarDTO newCarDTO, int id) {
//        Car newCar = carMapper.toEntity(newCarDTO);
//        Car updateCar = carRepository.findById(id)
//                .map(car -> {
//                    car.setName(newCar.getName());
//                    car.setVinCode(newCar.getVinCode());
//                    car.setCustomer(newCar.getCustomer());
//                    return carRepository.save(car);
//                }).orElseThrow(() -> new CarNotFoundException(id));
//        return carMapper.toDTO(updateCar);
//    }


//    private CarDTO toDTO(Car car) {
//        modelMapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.LOOSE);
//        return modelMapper.map(car, CarDTO.class);
//    }
//    private Car toEntity(CarDTO carDTO) {
//        modelMapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.LOOSE);
//        return modelMapper.map(carDTO, Car.class);
//    }

}
