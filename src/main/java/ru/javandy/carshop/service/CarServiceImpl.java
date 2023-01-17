package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.dto.CustomerDto;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.exeption.CarNotFoundException;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.repository.CarRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final CarMapper carMapper;

    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarDto saveCar(CarDto carDto) {
        return carMapper.toDto(carRepository.save(carMapper.toEntity(carDto)));
    }

    public CarDto findByCarId(int id) {
        return carMapper.toDto(carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id)));
    }

    public CarDto updateCarId(CarDto newCarDto, int id) {
        return carMapper.toDto(carRepository.findById(id)
                .map(car -> {
                    car.setName(newCarDto.getName());
                    car.setVinCode(newCarDto.getVinCode());
                    return carRepository.save(car);
                }).orElseThrow(() -> new CarNotFoundException(id)));
    }

    public void deleteByCarId(int id) {
        Car carDel = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        CarDto carDtoDel = carMapper.toDto(carDel);
        CustomerDto customerCarDtoDel = customerService.findByCar(carDtoDel);
        List<OrderDto> ordersCarDtoDel = orderService.getAllOrdersCar(carDtoDel);
        ordersCarDtoDel.forEach(customer -> customer.setCar(null));
        orderService.saveOrders(ordersCarDtoDel);
        customerCarDtoDel.removeCarDto(carDtoDel);
        customerService.saveCustomer(customerCarDtoDel);
    }

    public boolean existsByCarId(int id) {
        return  carRepository.existsById(id);
    }
}
