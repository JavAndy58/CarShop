package ru.javandy.carshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.dto.CustomerDto;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.mapper.DetailMapper;
import ru.javandy.carshop.mapper.OrderMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.OrderRepository;
import ru.javandy.carshop.utils.ExcelOrderCustomer;

import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {OrderServiceImpl.class, OrderMapper.class})
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderMapper orderMapper;

    @MockBean
    private CarMapper carMapper;

    @MockBean
    private DetailMapper detailMapper;

    @MockBean
    private ExcelOrderCustomer excelOrderCustomer;

    @Test
    void getAllOrders() {
        CarDto carDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        CustomerDto customerDto2 = new CustomerDto("User2", "+79955885252");
        customerDto1.addCarDTO(carDTO);
        DetailDto detailDto1 = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDto detailDto2 = new DetailDto(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDto orderDto1 = new OrderDto(new Date(), 0, false, false, " ", carDTO, customerDto1, 0, 0);
        orderDto1.addDetailDto(detailDto1);
        orderDto1.addDetailDto(detailDto2);
        OrderDto orderDto2 = new OrderDto(new Date(), 150, false, false, " ", carDTO, customerDto2, 0, 0);
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderDtoList.add(orderDto1);
        orderDtoList.add(orderDto2);
        Car car = new Car(1, "Focus 2", "XXEERTY525SA626");
        Customer customer1 = new Customer(1, "User1", "+79998885252", Collections.singletonList(car));
        Customer customer2 = new Customer(2, "User2", "+79955885252", null);
        Detail detail1 = new Detail(1, "Реле поворота", 1, 100.0, 145.0, " ", false);
        Detail detail2 = new Detail(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false);
        Order order1 = new Order(1, new Date(), 0, false, false, " ", car, Arrays.asList(detail1, detail2), customer1);
        Order order2 = new Order(2, new Date(), 150, false, false, " ", null, null, customer2);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        when(orderRepository.findAll()).thenReturn(orderList);
        when(orderMapper.toDtoList(orderList)).thenReturn(orderDtoList);

        orderService.getAllOrders();
        verify(orderRepository, times(1)).findAll();
        verify(orderMapper, times(1)).toDtoList(orderList);

    }

    @Test
    void saveOrder() {
        CarDto carDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDTO);
        DetailDto detailDto1 = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDto detailDto2 = new DetailDto(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDto orderDto1 = new OrderDto(new Date(), 0, false, false, " ", carDTO, customerDto1, 0, 0);
        orderDto1.addDetailDto(detailDto1);
        orderDto1.addDetailDto(detailDto2);
        Car car = new Car(1, "Focus 2", "XXEERTY525SA626");
        Customer customer1 = new Customer(1, "User1", "+79998885252", Collections.singletonList(car));
        Detail detail1 = new Detail(1, "Реле поворота", 1, 100.0, 145.0, " ", false);
        Detail detail2 = new Detail(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false);
        Order order1 = new Order(1, new Date(), 0, false, false, " ", car, Arrays.asList(detail1, detail2), customer1);

        when(orderMapper.toEntity(orderDto1)).thenReturn(order1);
        when(orderRepository.save(order1)).thenReturn(order1);
        when(orderMapper.toDto(order1)).thenReturn(orderDto1);

        orderService.saveOrder(orderDto1);
        verify(orderRepository, times(1)).save(order1);
        verify(orderMapper, times(1)).toEntity(orderDto1);
        verify(orderMapper, times(1)).toDto(order1);

    }

    @Test
    void findByOrderId() {
        int testId = 1;
        CarDto carDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDTO);
        DetailDto detailDto1 = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDto detailDto2 = new DetailDto(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDto orderDto1 = new OrderDto(new Date(), 0, false, false, " ", carDTO, customerDto1, 0, 0);
        orderDto1.addDetailDto(detailDto1);
        orderDto1.addDetailDto(detailDto2);
        Car car = new Car(1, "Focus 2", "XXEERTY525SA626");
        Customer customer1 = new Customer(1, "User1", "+79998885252", Collections.singletonList(car));
        Detail detail1 = new Detail(1, "Реле поворота", 1, 100.0, 145.0, " ", false);
        Detail detail2 = new Detail(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false);
        Order order1 = new Order(1, new Date(), 0, false, false, " ", car, Arrays.asList(detail1, detail2), customer1);

        when(orderRepository.findById(testId)).thenReturn(Optional.of(order1));
        when(orderMapper.toDto(order1)).thenReturn(orderDto1);

        orderService.findByOrderId(testId);
        verify(orderRepository, times(1)).findById(testId);
        verify(orderMapper, times(1)).toDto(order1);
    }

    @Test
    void findByDetail() {
        CarDto carDTO = new CarDto(1, "Focus 2", "XXEERTY525SA626");
        CustomerDto customerDto1 = new CustomerDto("User1", "+79998885252");
        customerDto1.addCarDTO(carDTO);
        DetailDto detailDto1 = new DetailDto(1, "Реле поворота", 1, 100.0, 145.0, " ", false, 0);
        DetailDto detailDto2 = new DetailDto(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false, 0);
        OrderDto orderDto1 = new OrderDto(new Date(), 0, false, false, " ", carDTO, customerDto1, 0, 0);
        orderDto1.addDetailDto(detailDto1);
        orderDto1.addDetailDto(detailDto2);
        Car car = new Car(1, "Focus 2", "XXEERTY525SA626");
        Customer customer1 = new Customer(1, "User1", "+79998885252", Collections.singletonList(car));
        Detail detail1 = new Detail(1, "Реле поворота", 1, 100.0, 145.0, " ", false);
        Detail detail2 = new Detail(2, "Фара 2107", 1, 10000.0, 14005.0, " ", false);
        Order order1 = new Order(1, new Date(), 0, false, false, " ", car, Arrays.asList(detail1, detail2), customer1);

        when(orderRepository.findByDetails(detail1)).thenReturn(order1);
        when(detailMapper.toEntity(detailDto1)).thenReturn(detail1);
        when(orderMapper.toDto(order1)).thenReturn(orderDto1);

        orderService.findByDetail(detailDto1);
        verify(orderRepository, times(1)).findByDetails(detail1);
    }
}