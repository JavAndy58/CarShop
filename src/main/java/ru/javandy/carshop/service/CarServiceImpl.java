package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.exeption.CarNotFoundException;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.CarRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CustomerService customerService,
                          OrderService orderService, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.customerService = customerService;
        this.orderService = orderService;
        this.carMapper = carMapper;
    }

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
        CarDTO carDelDTO = carMapper.toDTO(carDel);
        CustomerDTO customerCarDelDTO = customerService.findByCars(carDelDTO);

        List<OrderDTO> ordersCarDelDTO = orderService.getAllOrdersCar(customerCarDelDTO, carDelDTO);

        ordersCarDelDTO.forEach(customer -> customer.setCar(null));
        orderService.saveOrders(ordersCarDelDTO);

        customerCarDelDTO.removeCarDTO(carDelDTO);

        customerService.saveCustomer(customerCarDelDTO);
    }

    public boolean existsByCarId(int id) {
        return  carRepository.existsById(id);
    }
}
