package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService mockCustomerService;

    private static final String BASE_URL = "/customer";

    @Test
    void getAllCustomers_whenGetCustomers_thenStatus200() throws Exception {
        CarDTO carDTO1 = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        CarDTO carDTO2 = new CarDTO(2, "Logan 1", "TTTYYY525SA626");
        CustomerDTO customerDTO1 = new CustomerDTO("User1", "+79998885252");
        customerDTO1.addCarDTO(carDTO1);
        customerDTO1.addCarDTO(carDTO2);
        CustomerDTO customerDTO2 = new CustomerDTO("User2", "+79997776363");
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO1);
        customerDTOList.add(customerDTO2);
        String expectedJson = objectMapper.writeValueAsString(customerDTOList);

        Mockito.doReturn(customerDTOList).when(mockCustomerService).getAllCustomers();

        mockMvc.perform(get(BASE_URL + "s"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void createCustomer_whenAddCustomer_thenStatus200andCustomerReturned() throws Exception {
        CustomerDTO savedCustomerDTO = new CustomerDTO("User", "+79998885252");
        String savedJson = objectMapper.writeValueAsString(savedCustomerDTO);

        Mockito.when(mockCustomerService.saveCustomer(savedCustomerDTO)).thenReturn(savedCustomerDTO);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void getCustomerId_whenGetExistingCustomer_thenStatus200andCustomerReturned() throws Exception {
        int id = 1;
        CustomerDTO expectedCustomerDTO = new CustomerDTO("User", "+79998885252");
        String expectedJson = objectMapper.writeValueAsString(expectedCustomerDTO);

        Mockito.doReturn(expectedCustomerDTO).when(mockCustomerService).findByCustomerId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void updateCustomer_whenUpdate_thenStatus200andReturns() throws Exception {
        int id = 1;
        CustomerDTO updateCustomerDTO = new CustomerDTO("User", "+79998885252");
        String updateAsJson = objectMapper.writeValueAsString(updateCustomerDTO);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateAsJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}