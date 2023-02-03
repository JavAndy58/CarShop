package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto saveOrder(OrderDto orderDTO);
    List<OrderDto> saveOrders(List<OrderDto> ordersDTO);
    OrderDto findByOrderId(int id);
    OrderDto updateOrderId(OrderDto orderDTO, int id);
    List<OrderDto> getAllOrdersCar(CarDto carDTO);
    OrderDto findByDetail(DetailDto detailDTO);
    void printOrderId(int id);
}
