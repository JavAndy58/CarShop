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
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.dto.DetailDto;
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
    void getAllOrdersWhenGetOrdersThenStatus200() throws Exception {
        CarDto carDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        CustomerDto customerDto2 = new CustomerDto("User2", "+79955885252");
        customerDto1.addCarDTO(carDTO);
        DetailDto detailDto1 = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDto detailDto2 = new DetailDto(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        DetailDto detailDto3 = new DetailDto(3, "Клипса двери", 10, 30.0, 45.0, " ", false, 0);
        OrderDto orderDto1 = new OrderDto(new Date(), 0, false, false, " ", carDTO, customerDto1, 0, 0);
        orderDto1.addDetailDto(detailDto1);
        orderDto1.addDetailDto(detailDto2);
        OrderDto orderDto2 = new OrderDto(new Date(), 150, false, false, " ", carDTO, customerDto2, 0, 0);
        orderDto2.addDetailDto(detailDto3);
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderDtoList.add(orderDto1);
        orderDtoList.add(orderDto2);
        String expectedJson = objectMapper.writeValueAsString(orderDtoList);

        Mockito.doReturn(orderDtoList).when(mockOrderService).getAllOrders();

        mockMvc.perform(get(BASE_URL + "s"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void createOrderWhenAddOrderThenStatus200andOrderReturned() throws Exception {
        CarDto carDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDTO);
        DetailDto detailDto1 = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDto detailDto2 = new DetailDto(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDto savedOrderDto = new OrderDto(new Date(), 0, false, false, " ", carDTO, customerDto1, 0, 0);
        savedOrderDto.addDetailDto(detailDto1);
        savedOrderDto.addDetailDto(detailDto2);
        String savedJson = objectMapper.writeValueAsString(savedOrderDto);

        Mockito.when(mockOrderService.saveOrder(savedOrderDto)).thenReturn(savedOrderDto);

        mockMvc.perform(post(BASE_URL).content(savedJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(savedJson, true));
    }

    @Test
    void getOrderIdWhenGetExistingOrderThenStatus200andOrderReturned() throws Exception {
        int id = 1;
        CarDto carDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDTO);
        DetailDto detailDto1 = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDto detailDto2 = new DetailDto(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDto expectedOrderDto = new OrderDto(new Date(), 0, false, false, " ", carDTO, customerDto1, 0, 0);
        expectedOrderDto.addDetailDto(detailDto1);
        expectedOrderDto.addDetailDto(detailDto2);
        String expectedJson = objectMapper.writeValueAsString(expectedOrderDto);

        Mockito.doReturn(expectedOrderDto).when(mockOrderService).findByOrderId(id);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void updateOrderWhenUpdateThenStatus200andReturns() throws Exception {
        int id = 1;
        CarDto carDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDTO);
        DetailDto detailDto1 = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDto detailDto2 = new DetailDto(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDto updateOrderDto = new OrderDto(new Date(), 0, false, false, " ", carDTO, customerDto1, 0, 0);
        updateOrderDto.addDetailDto(detailDto1);
        updateOrderDto.addDetailDto(detailDto2);
        String updateAsJson = objectMapper.writeValueAsString(updateOrderDto);

        mockMvc.perform(put(BASE_URL + "/" + id).content(updateAsJson).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}