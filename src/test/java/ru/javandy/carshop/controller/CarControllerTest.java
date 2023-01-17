package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.service.CarService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("checkstyle:Indentation")
@WebMvcTest(controllers = CarController.class)
class CarControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService mockCarService;

    private static final String BASE_URL = "/car";

    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @Test
    void getAllCarsWhenGetCarsThenStatus200() throws Exception {
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CarDto carDto2 = new CarDto(2, "Logan 1", "TTTYYY525SA626");
        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList.add(carDto1);
        carDtoList.add(carDto2);

        String expectedJson = objectMapper.writeValueAsString(carDtoList);

        Mockito.doReturn(carDtoList)
                        .when(mockCarService).getAllCars();

        mockMvc.perform(get(BASE_URL + "s"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void getCarIdWhenGetExistingCarThenStatus200andCarReturned() throws Exception {
        int id = 2;
        CarDto expectedCarDto = new CarDto(id, "Focus 2", "XXEERTY525SA626");
        String expectedJson = objectMapper.writeValueAsString(expectedCarDto);

        Mockito.doReturn(expectedCarDto).when(mockCarService).findByCarId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void createCarWhenAddCarThenStatus200andCarReturned() throws Exception {
        CarDto savedDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        String savedJson = objectMapper.writeValueAsString(savedDTO);

        Mockito.when(mockCarService.saveCar(savedDTO)).thenReturn(savedDTO);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void updateCarWhenUpdateThenStatus200andUpdateReturns() throws Exception {
        int id = 1;
        CarDto updateCarDto = new CarDto(id, "Logan1", "XTY52A626");
        String updateAsJson = objectMapper.writeValueAsString(updateCarDto);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateAsJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteCarWhenDeleteCarThenStatus404() throws Exception {
        int id = 1;

        mockMvc.perform(delete(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}