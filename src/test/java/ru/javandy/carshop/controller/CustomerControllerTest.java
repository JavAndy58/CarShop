package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.dto.CustomerDto;
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
    void getAllCustomersWhenGetCustomersThenStatus200() throws Exception {
        CarDto carDto1 = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDto1);
        CustomerDto customerDto2 = new CustomerDto("User2", "+79997776363");
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerDtoList.add(customerDto1);
        customerDtoList.add(customerDto2);
        String expectedJson = objectMapper.writeValueAsString(customerDtoList);

        Mockito.doReturn(customerDtoList).when(mockCustomerService).getAllCustomers();

        mockMvc.perform(get(BASE_URL + "s"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void createCustomerWhenAddCustomerThenStatus200andCustomerReturned() throws Exception {
        CustomerDto savedCustomerDto = new CustomerDto("User", "+79998885252");
        String savedJson = objectMapper.writeValueAsString(savedCustomerDto);

        Mockito.when(mockCustomerService.saveCustomer(savedCustomerDto)).thenReturn(savedCustomerDto);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void getCustomerIdWhenGetExistingCustomerThenStatus200andCustomerReturned() throws Exception {
        int id = 1;
        CustomerDto expectedCustomerDto = new CustomerDto("User", "+79998885252");
        String expectedJson = objectMapper.writeValueAsString(expectedCustomerDto);

        Mockito.doReturn(expectedCustomerDto).when(mockCustomerService).findByCustomerId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void updateCustomerWhenUpdateThenStatus200andReturns() throws Exception {
        int id = 1;
        CustomerDto updateCustomerDto = new CustomerDto("User", "+79998885252");
        String updateAsJson = objectMapper.writeValueAsString(updateCustomerDto);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateAsJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}