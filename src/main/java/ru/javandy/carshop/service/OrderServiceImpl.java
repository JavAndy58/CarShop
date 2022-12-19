package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.exeption.OrderNotFoundException;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.OrderRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> saveOrders(List<Order> orders) {
        return orderRepository.saveAll(orders);
    }

    public Order findByOrderId(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id));
    }

    public Order updateOrderId(Order newOrder, int id) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCreated(newOrder.getCreated());
                    order.setPrepayment(newOrder.getPrepayment());
                    order.setDelivered(newOrder.isDelivered());
                    order.setCardPayment(newOrder.isCardPayment());
                    order.setNote(newOrder.getNote());
                    order.setCar(newOrder.getCar());


//                    order.setDetails(newOrder.getDetails());




                    order.setCustomer(newOrder.getCustomer());
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public boolean existsByOrderId(int id) {
        return orderRepository.existsById(id);
    }

    public void deleteByOrderId(int id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrderCustomerAndCar(Customer customer, Car car) {
        return orderRepository.findByCustomerAndCar(customer, car);
    }
}
