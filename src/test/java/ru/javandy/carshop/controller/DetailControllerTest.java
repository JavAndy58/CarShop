package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.DetailDTO;
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
    void createDetail_whenAddDetail_thenStatus200andDetailReturned() throws Exception {
        DetailDTO savedDTO = new DetailDTO(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 100.0);
        String savedJson = objectMapper.writeValueAsString(savedDTO);

        Mockito.when(mockDetailService.saveDetail(savedDTO)).thenReturn(savedDTO);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void getDetailId_whenGetExistingDetail_thenStatus200andDetailReturned() throws Exception {
        int id = 1;
        DetailDTO expectedDetailDTO = new DetailDTO(id, "Реле поворота", 1, 100.0, 145.0, " ", false, 100.0);
        String expectedJson = objectMapper.writeValueAsString(expectedDetailDTO);

        Mockito.doReturn(expectedDetailDTO).when(mockDetailService).findByDetailId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void updateDetail_whenUpdate_thenStatus200andUpdateReturns() throws Exception {
        int id = 1;
        DetailDTO updateDetailDTO = new DetailDTO(id, "Реле поворота", 1, 100.0, 145.0, " ", false, 100.0);
        String updateASJson = objectMapper.writeValueAsString(updateDetailDTO);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateASJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteDetail_whenDeledeDetail_thenStatus404() throws Exception {
        int id = 1;

        mockMvc.perform(delete(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}