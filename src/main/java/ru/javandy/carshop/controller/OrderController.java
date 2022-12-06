package ru.javandy.carshop.controller;

import org.springframework.web.bind.annotation.*;
import ru.javandy.carshop.exeption.OrderNotFoundException;
import ru.javandy.carshop.model.Order;
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
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/order/{id}")
    Order getOrderById(@PathVariable int id) {
        return orderService.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PutMapping("/order/{id}")
    Order updateOrder(@RequestBody Order newOrder, @PathVariable int id) {
        return orderService.findById(id)
                .map(order -> {
                    order.setPrepayment(newOrder.getPrepayment());
                    order.setDelivered(newOrder.isDelivered());
                    order.setCardPayment(newOrder.isCardPayment());
                    order.setNote(newOrder.getNote());
                    order.setCar(newOrder.getCar());
                    order.setDetails(newOrder.getDetails());
                    order.setCustomer(newOrder.getCustomer());
                    return orderService.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @DeleteMapping("/order/{id}")
    String deleteOrder(@PathVariable int id) {
        if (!orderService.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderService.deleteById(id);
        return "Order with id " + id + " has been deleted success.";
    }
}
