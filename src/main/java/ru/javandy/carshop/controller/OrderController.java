package ru.javandy.carshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @GetMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrderId(@PathVariable int id) {
        return orderService.findByOrderId(id);
    }

    @PutMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable int id) {
        return orderService.updateOrderId(orderDTO, id);
    }
}
