package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.exeption.OrderNotFoundException;
import ru.javandy.carshop.mapper.OrderMapper;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.service.OrderService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/order")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        return orderMapper.toDTO(orderService.saveOrder(order));
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrderId(@PathVariable int id) {
        Order order = orderService.findByOrderId(id);
        return orderMapper.toDTO(order);
    }

    @PutMapping("/order/{id}")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable int id) {
        Order order = orderMapper.toEntity(orderDTO);
        return orderMapper.toDTO(orderService.updateOrderId(order, id));
    }

    @DeleteMapping("/order/{id}")
    String deleteByOrderId(@PathVariable int id) {
        if (!orderService.existsByOrderId(id)) {
            throw new OrderNotFoundException(id);
        }
        orderService.deleteByOrderId(id);
        return "Order with id " + id + " has been deleted success.";
    }
}
