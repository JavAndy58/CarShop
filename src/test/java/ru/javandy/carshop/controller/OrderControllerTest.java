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
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.dto.DetailDTO;
import ru.javandy.carshop.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService mockOrderService;

    private static final String BASE_URL = "/order";

    @Test
    void getAllOrders_whenGetOrders_thenStatus200() throws Exception {
        CarDTO carDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        CustomerDTO customerDTO1 = new CustomerDTO("User1", "+79998885252");
        CustomerDTO customerDTO2 = new CustomerDTO("User2", "+79955885252");
        customerDTO1.addCarDTO(carDTO);
        DetailDTO detailDTO1 = new DetailDTO(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDTO detailDTO2 = new DetailDTO(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        DetailDTO detailDTO3 = new DetailDTO(3, "Клипса двери", 10, 30.0, 45.0, " ", false, 0);
        OrderDTO orderDTO1 = new OrderDTO(new Date(), 0, false, false, " ", carDTO, customerDTO1, 0, 0);
        orderDTO1.addDetailDTO(detailDTO1);
        orderDTO1.addDetailDTO(detailDTO2);
        OrderDTO orderDTO2 = new OrderDTO(new Date(), 150, false, false, " ", carDTO, customerDTO2, 0, 0);
        orderDTO2.addDetailDTO(detailDTO3);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList.add(orderDTO1);
        orderDTOList.add(orderDTO2);
        String expectedJson = objectMapper.writeValueAsString(orderDTOList);

        Mockito.doReturn(orderDTOList).when(mockOrderService).getAllOrders();

        mockMvc.perform(get(BASE_URL + "s"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void createOrder_whenAddOrder_thenStatus200andOrderReturned() throws Exception {
        CarDTO carDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        CustomerDTO customerDTO1 = new CustomerDTO("User1", "+79998885252");
        customerDTO1.addCarDTO(carDTO);
        DetailDTO detailDTO1 = new DetailDTO(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDTO detailDTO2 = new DetailDTO(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDTO savedOrderDTO = new OrderDTO(new Date(), 0, false, false, " ", carDTO, customerDTO1, 0, 0);
        savedOrderDTO.addDetailDTO(detailDTO1);
        savedOrderDTO.addDetailDTO(detailDTO2);
        String savedJson = objectMapper.writeValueAsString(savedOrderDTO);

        Mockito.when(mockOrderService.saveOrder(savedOrderDTO)).thenReturn(savedOrderDTO);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void getOrderId_whenGetExistingOrder_thenStatus200andOrderReturned() throws Exception {
        int id = 1;
        CarDTO carDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        CustomerDTO customerDTO1 = new CustomerDTO("User1", "+79998885252");
        customerDTO1.addCarDTO(carDTO);
        DetailDTO detailDTO1 = new DetailDTO(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDTO detailDTO2 = new DetailDTO(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDTO expectedOrderDTO = new OrderDTO(new Date(), 0, false, false, " ", carDTO, customerDTO1, 0, 0);
        expectedOrderDTO.addDetailDTO(detailDTO1);
        expectedOrderDTO.addDetailDTO(detailDTO2);
        String expectedJson = objectMapper.writeValueAsString(expectedOrderDTO);

        Mockito.doReturn(expectedOrderDTO).when(mockOrderService).findByOrderId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void updateOrder_whenUpdate_thenStatus200andReturns() throws Exception {
        int id = 1;
        CarDTO carDTO = new CarDTO(1, "Focus 2", "XXEERTY525SA626");
        CustomerDTO customerDTO1 = new CustomerDTO("User1", "+79998885252");
        customerDTO1.addCarDTO(carDTO);
        DetailDTO detailDTO1 = new DetailDTO(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDTO detailDTO2 = new DetailDTO(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDTO updateOrderDTO = new OrderDTO(new Date(), 0, false, false, " ", carDTO, customerDTO1, 0, 0);
        updateOrderDTO.addDetailDTO(detailDTO1);
        updateOrderDTO.addDetailDTO(detailDTO2);
        String updateAsJson = objectMapper.writeValueAsString(updateOrderDTO);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateAsJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}