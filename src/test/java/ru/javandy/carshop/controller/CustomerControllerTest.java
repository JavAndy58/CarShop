package ru.javandy.carshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.dto.CustomerDTO;
import ru.javandy.carshop.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    private static final String BASE_URL = "/customer";

    @Test
    void getAllCustomers_whenGetCustomers_thenStatus200() throws Exception {
        CarDTO carDTO1 = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        CarDTO carDTO2 = new CarDTO(2, "Logan 1", "TTTYYY525SA626");

        CustomerDTO customerDTO1 = new CustomerDTO("User1", "+79998885252");
        customerDTO1.addCarDTO(carDTO1);
        customerDTO1.addCarDTO(carDTO2);
        CustomerDTO customerDTO2 = new CustomerDTO("User2", "+79997776363");

    }

    @Test
    void getCustomerId() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }
}