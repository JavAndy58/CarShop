package ru.javandy.carshop.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.repository.CarRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { CarServiceImpl.class, CarMapper.class })
class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CarMapper carMapper;

    @Test
    void getAllCars() throws Exception {
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CarDto carDto2 = new CarDto(2, "Logan 1", "TTTYYY525SA626");
        Car car1 = new Car(1, "Focus 2", "XXEERTY525SA626");
        Car car2 = new Car(2, "Logan 1", "TTTYYY525SA626");

        when(carMapper.toDto(car1)).thenReturn(carDto1);
        when(carMapper.toDto(car2)).thenReturn(carDto2);
        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        carService.getAllCars();
        verify(carRepository, times(1)).findAll();
        verify(carMapper, times(1)).toDto(car1);
        verify(carMapper, times(1)).toDto(car2);
    }

    @Test
    void saveCar() throws Exception {
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        Car car1 = new Car(1, "Focus 2", "XXEERTY525SA626");

        when(carMapper.toEntity(carDto1)).thenReturn(car1);
        when(carRepository.save(car1)).thenReturn(car1);
        when(carMapper.toDto(car1)).thenReturn(carDto1);

        carService.saveCar(carDto1);
        verify(carRepository, times(1)).save(car1);
        verify(carMapper, times(1)).toEntity(carDto1);
        verify(carMapper, times(1)).toDto(car1);
    }

    @Test
    void findByCarId() throws Exception  {
        int id = 1;
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        Car car1 = new Car(1, "Focus 2", "XXEERTY525SA626");
        Optional<Car> optionalCar = Optional.of(car1);

        when(carRepository.findById(id)).thenReturn(optionalCar);
        when(carMapper.toDto(car1)).thenReturn(carDto1);

        carService.findByCarId(id);
        verify(carRepository, times(1)).findById(id);
        verify(carMapper, times(1)).toDto(car1);
    }

    @Test
    void updateCarId() throws Exception {
        int id = 1;
        Car car1 = new Car(1, "Focus 2", "XXEERTY525SA626");
        Car car2 = new Car("Logan 1", "TTTYYY525SA626");
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");

        Optional<Car> optionalCar1 = Optional.of(car1);

        when(carRepository.findById(id)).thenReturn(optionalCar1);
        when(carMapper.toDto(car1)).thenReturn(carDto1);
        when(carRepository.save(car1)).thenReturn(car2);

        carService.updateCarId(carDto1, id);
        verify(carRepository, times(1)).findById(id);
        verify(carRepository, times(1)).save(car1);
    }
}