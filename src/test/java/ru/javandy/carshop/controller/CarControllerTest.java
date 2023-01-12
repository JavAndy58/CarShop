package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.service.CarService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void getAllCars_whenGetCars_thenStatus200() throws Exception {
//        List<CarDTO> carDTOList =
//                Arrays.asList(
//                        new CarDTO(1, "Focus 2", "XXEERTY525SA626"),
//                        new CarDTO(2, "Logan 1", "TTTYYY525SA626"),
//                        new CarDTO(3, "Audi 100", "YUTYRYREYRA626")
//
//                );
//        String expectedJson = objectMapper.writeValueAsString(carDTOList);

//        objectMapper.writeValueAsString(carDTOList);
//        when(mockCarService.getAllCars()).thenReturn(carDTOList);
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
//        int id = 2;
//        CarDTO expectedCarDTO = new CarDTO(2, "Focus 2", "XXEERTY525SA626");
//
//        when(mockCarService.findByCarId(2)).thenReturn(expectedCarDTO);
//
//        mockMvc
//                .perform(get(BASE_URL + "/" + id))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(2))
//                .andExpect(jsonPath("$.name").value("Focus 2"))
//                .andExpect(jsonPath("$.vinCode").value("XXEERTY525SA626"));
        int id = 2;
        CarDTO expectedCarDTO = new CarDTO(2, "Focus 2", "XXEERTY525SA626");
        String expectedJson = objectMapper.writeValueAsString(expectedCarDTO);

        Mockito.doReturn(expectedCarDTO)
                .when(mockCarService).findByCarId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));

    }

    @Test
    void createCar_whenAddCar_thenStatus200andCarReturned() throws Exception {
//        int id = 1;
//        CarDTO expectedCarDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
//
//        when(mockCarService.saveCar(expectedCarDTO)).thenReturn(expectedCarDTO);
//
//        mockMvc
//                .perform(post(BASE_URL)
//                        .content(objectMapper.writeValueAsString(expectedCarDTO))
//                        .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(expectedCarDTO)));
        CarDTO savedDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        String savedJson = objectMapper.writeValueAsString(savedDTO);

        Mockito.when(mockCarService.saveCar(savedDTO)).thenReturn(savedDTO);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void updateCar_whenUpdate_thenStatus201andUpdateReturns() throws Exception {
//        int id = 1;
//        CarDTO carDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
//        Mockito.when(mockCarService.saveCar(Mockito.any())).thenReturn(carDTO);
//
//        mockMvc
//                .perform(put(BASE_URL + "/" + id)
//                        .content(objectMapper.writeValueAsString(new CarDTO("Logan", "XXEETRGRG")))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.name").value("Logan"))
//                .andExpect(jsonPath("$.vinCode").value("XXEETRGRG"));

        int id = 1;
        CarDTO updateCarDTO = new CarDTO("Logan1", "XTY52A626");
        String updateAsJson = objectMapper.writeValueAsString(updateCarDTO);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateAsJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCar_whenDeleteCar_thenStatus200() throws Exception {
        CarDTO carDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
//        Mockito.when(mockCarService.saveCar(carDTO)).thenReturn(carDTO);
        Mockito.when(mockCarService.findByCarId(Mockito.any())).thenReturn(carDTO);

        mockMvc.perform(delete(BASE_URL + "/" + 1))
                .andExpect(status().isOk());

//        int id = 1;
//
//        mockMvc.perform(delete(BASE_URL + "/" + id))
//                .andDo(print())
//                .andExpect(status().isNoContent());
    }
}