package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.CarDTO;
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
    void getAllCars_whenGetCars_thenStatus200() throws Exception {
        CarDTO carDTO1 = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        CarDTO carDTO2 = new CarDTO(2, "Logan 1", "TTTYYY525SA626");
        List<CarDTO> carDTOList = new ArrayList<>();
        carDTOList.add(carDTO1);
        carDTOList.add(carDTO2);

        String expectedJson = objectMapper.writeValueAsString(carDTOList);

        Mockito.doReturn(carDTOList)
                        .when(mockCarService).getAllCars();

        mockMvc.perform(get(BASE_URL + "s"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void getCarId_whenGetExistingCar_thenStatus200andCarReturned() throws Exception {
        int id = 2;
        CarDTO expectedCarDTO = new CarDTO(id, "Focus 2", "XXEERTY525SA626");
        String expectedJson = objectMapper.writeValueAsString(expectedCarDTO);

        Mockito.doReturn(expectedCarDTO).when(mockCarService).findByCarId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void createCar_whenAddCar_thenStatus200andCarReturned() throws Exception {
        CarDTO savedDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        String savedJson = objectMapper.writeValueAsString(savedDTO);

        Mockito.when(mockCarService.saveCar(savedDTO)).thenReturn(savedDTO);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void updateCar_whenUpdate_thenStatus200andUpdateReturns() throws Exception {
        int id = 1;
        CarDTO updateCarDTO = new CarDTO(id, "Logan1", "XTY52A626");
        String updateAsJson = objectMapper.writeValueAsString(updateCarDTO);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateAsJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteCar_whenDeleteCar_thenStatus404() throws Exception {
        int id = 1;

        mockMvc.perform(delete(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}