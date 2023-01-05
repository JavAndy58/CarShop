package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.dto.OrderDTO;
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

    public List<CarDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CarDTO saveCar(CarDTO carDTO) {
        return carMapper.toDTO(carRepository.save(carMapper.toEntity(carDTO)));
    }

    public CarDTO findByCarId(int id) {
        return carMapper.toDTO(carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id)));
    }

    public CarDTO updateCarId(CarDTO newCarDTO, int id) {
        return carMapper.toDTO(carRepository.findById(id)
                .map(car -> {
                    car.setName(newCarDTO.getName());
                    car.setVinCode(newCarDTO.getVinCode());
                    return carRepository.save(car);
                }).orElseThrow(() -> new CarNotFoundException(id)));
    }

    public void deleteByCarId(int id) {
        Car carDel = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        CarDTO carDTODel = carMapper.toDTO(carDel);
        CustomerDTO customerCarDTODel = customerService.findByCar(carDTODel);
        List<OrderDTO> ordersCarDTODel = orderService.getAllOrdersCar(carDTODel);
        ordersCarDTODel.forEach(customer -> customer.setCar(null));
        orderService.saveOrders(ordersCarDTODel);
        customerCarDTODel.removeCarDTO(carDTODel);
        customerService.saveCustomer(customerCarDTODel);
    }

    public boolean existsByCarId(int id) {
        return  carRepository.existsById(id);
    }
}
