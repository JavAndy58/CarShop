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
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CarDto saveCar(CarDto carDTO) {
        return carMapper.toDTO(carRepository.save(carMapper.toEntity(carDTO)));
    }

    public CarDto findByCarId(int id) {
        return carMapper.toDTO(carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id)));
    }

    public CarDto updateCarId(CarDto newCarDto, int id) {
        return carMapper.toDTO(carRepository.findById(id)
                .map(car -> {
                    car.setName(newCarDto.getName());
                    car.setVinCode(newCarDto.getVinCode());
                    return carRepository.save(car);
                }).orElseThrow(() -> new CarNotFoundException(id)));
    }

    public void deleteByCarId(int id) {
        Car carDel = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        CarDto carDtoDel = carMapper.toDTO(carDel);
        CustomerDto customerCarDTODel = customerService.findByCar(carDtoDel);
        List<OrderDto> ordersCarDTODel = orderService.getAllOrdersCar(carDtoDel);
        ordersCarDTODel.forEach(customer -> customer.setCar(null));
        orderService.saveOrders(ordersCarDTODel);
        customerCarDTODel.removeCarDTO(carDtoDel);
        customerService.saveCustomer(customerCarDTODel);
    }

    public boolean existsByCarId(int id) {
        return  carRepository.existsById(id);
    }
}
