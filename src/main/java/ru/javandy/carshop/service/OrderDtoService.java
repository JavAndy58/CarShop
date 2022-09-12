package ru.javandy.carshop.service;

import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.OrderRepository;
import ru.javandy.carshop.utils.MappingUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDtoService {
    private final OrderRepository orderRepository;
    private final MappingUtils mappingUtils;

    public OrderDtoService(OrderRepository orderRepository, MappingUtils mappingUtils) {
        this.orderRepository = orderRepository;
        this.mappingUtils = mappingUtils;
    }

//    public List<OrderDto> findAll() {
//        List<Order> rsl = new ArrayList<>();
//        orderRepository.findAll().forEach(rsl::add);
//        return rsl.stream()
//                .map(mappingUtils::mapTOOrderDto)
//                .forEach(orderDto -> orderDto.setSumDetail());
    }



}
