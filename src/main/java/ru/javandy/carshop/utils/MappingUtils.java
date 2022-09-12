package ru.javandy.carshop.utils;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.model.Order;

@Service
public class MappingUtils {

    public OrderDto mapTOOrderDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCreated(order.getCreated());
        dto.setCustomer(order.getCustomer());
        dto.setDetails(order.getDetails());
        dto.setPrepayment(order.getPrepayment());
        dto.setDelivered(order.isDelivered());
        return dto;
    }

    public Order mapToOrder(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setCreated(dto.getCreated());
        order.setCustomer(dto.getCustomer());
        order.setDetails(dto.getDetails());
        order.setPrepayment(dto.getPrepayment());
        order.setDelivered(dto.isDelivered());
        return order;
    }
}
