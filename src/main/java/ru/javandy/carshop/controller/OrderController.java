package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @GetMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderId(@PathVariable int id) {
        return orderService.findByOrderId(id);
    }

    @PutMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrder(@RequestBody OrderDto orderDTO, @PathVariable int id) {
        return orderService.updateOrderId(orderDTO, id);
    }
}
