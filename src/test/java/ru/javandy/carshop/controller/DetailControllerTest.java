package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.service.DetailService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DetailController.class)
class DetailControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DetailService mockDetailService;

    private static final String BASE_URL = "/detail";

    @Test
    void createDetailWhenAddDetailThenStatus200andDetailReturned() throws Exception {
        DetailDto savedDTO = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 100.0);
        String savedJson = objectMapper.writeValueAsString(savedDTO);

        Mockito.when(mockDetailService.saveDetail(savedDTO)).thenReturn(savedDTO);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void getDetailIdWhenGetExistingDetailThenStatus200andDetailReturned() throws Exception {
        int id = 1;
        DetailDto expectedDetailDto = new DetailDto(id, "Реле поворота", 1, 100.0, 145.0, " ", false, 100.0);
        String expectedJson = objectMapper.writeValueAsString(expectedDetailDto);

        Mockito.doReturn(expectedDetailDto).when(mockDetailService).findByDetailId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void updateDetailWhenUpdateThenStatus200andUpdateReturns() throws Exception {
        int id = 1;
        DetailDto updateDetailDto = new DetailDto(id, "Реле поворота", 1, 100.0, 145.0, " ", false, 100.0);
        String updateASJson = objectMapper.writeValueAsString(updateDetailDto);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateASJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteDetailWhenDeledeDetailThenStatus404() throws Exception {
        int id = 1;

        mockMvc.perform(delete(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}