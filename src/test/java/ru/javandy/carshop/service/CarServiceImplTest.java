package ru.javandy.carshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.javandy.carshop.repository.CarRepository;

@SpringBootTest(classes = CarServiceImpl.class)
class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    void getAllCars_whenGetCars_thenStatus200() throws Exception {



















    }

    @Test
    void saveCar_whenAddCar_thenStatus200andCarReturned() throws Exception {
    }

    @Test
    void findByCarId_whenGetExistingCar_thenStatus200andCarReturned() throws Exception  {
    }

    @Test
    void updateCarId_whenUpdate_thenStatus200andUpdateReturns() throws Exception {
    }

    @Test
    void deleteByCarId_whenDeleteCar_thenStatus404() throws Exception {
    }

    @Test
    void existsByCarId() throws Exception  {
    }
}