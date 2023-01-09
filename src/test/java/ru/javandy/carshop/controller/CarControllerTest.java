package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.service.CarService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class CarControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CarService service;

    @Test
    void getAllCarsThenStatus200() throws Exception {
        CarDTO carDTO1 = new CarDTO("Ford", "XXXFFF");
        CarDTO carDTO2 = new CarDTO("Mazda", "MMMXXX");

        Mockito.when(service.getAllCars()).thenReturn(Arrays.asList(carDTO1, carDTO2));

        mockMvc.perform(
                get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(carDTO1, carDTO2))));

    }

    @Test
    void getCarId() {
    }

    @Test
    void createCar() {
    }

    @Test
    void updateCar() {
    }

    @Test
    void deleteCar() {
    }
}