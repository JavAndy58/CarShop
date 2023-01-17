package ru.javandy.carshop.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.repository.CarRepository;
import ru.javandy.carshop.service.CarServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CarServiceImpl.class)
class CarServiceImplTest {

    @Autowired
    private CarServiceImpl carService;

//    @Autowired
//    private CarMapper carMapper;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private CarMapper carMapper;

    @Test
    void getAllCarsWhenGetCarsThenStatus200() throws Exception {
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CarDto carDto2 = new CarDto(2, "Logan 1", "TTTYYY525SA626");
        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(carDto1);
        carDtoList.add(carDto2);


        Car car1 = carMapper.toEntity(carDto1);
        Car car2 = carMapper.toEntity(carDto2);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        Mockito.doReturn(carList).when(carRepository).findAll();
        List<CarDto> actual = carService.getAllCars();

        System.out.println("массив получился" + carList);
        assertThat(actual).size().isEqualTo(2);
//        assertThat(actual).isEqualTo(carDtoList);




    }

    @Test
    void saveCarWhenAddCarThenStatus200andCarReturned() throws Exception {
    }

    @Test
    void findByCarIdWhenGetExistingCarThenStatus200andCarReturned() throws Exception  {
    }

    @Test
    void updateCarIdWhenUpdateThenStatus200andUpdateReturns() throws Exception {
    }

    @Test
    void deleteByCarIdWhenDeleteCarThenStatus404() throws Exception {
    }

    @Test
    void existsByCarId() throws Exception  {
    }
}