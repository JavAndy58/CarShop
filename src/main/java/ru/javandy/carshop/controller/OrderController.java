package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.exeption.OrderNotFoundException;
import ru.javandy.carshop.service.OrderService;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/order")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrderId(@PathVariable int id) {
        return orderService.findByOrderId(id);
    }

    @PutMapping("/order/{id}")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable int id) {
        return orderService.updateOrderId(orderDTO, id);
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
