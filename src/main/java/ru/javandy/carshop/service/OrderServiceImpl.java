package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.exeption.OrderNotFoundException;
import ru.javandy.carshop.mapper.OrderMapper;
import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        return orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));
    }

    public List<OrderDTO> saveOrders(List<OrderDTO> ordersDTO) {
        return orderMapper.toDTO(orderRepository.saveAll(orderMapper.toEntity(ordersDTO)));
    }

    public OrderDTO findByOrderId(int id) {
        return orderMapper.toDTO(orderRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id)));
    }

    public OrderDTO updateOrderId(OrderDTO newOrderDTO, int id) {
        return orderMapper.toDTO(orderRepository.findById(id)
                .map(order -> {
                    order.setCreated(newOrderDTO.getCreated());
                    order.setPrepayment(newOrderDTO.getPrepayment());
                    order.setDelivered(newOrderDTO.isDelivered());
                    order.setCardPayment(newOrderDTO.isCardPayment());
                    order.setNote(newOrderDTO.getNote());
                    order.setCar(newOrderDTO.getCar());

//                    order.setDetails(newOrder.getDetails());

                    order.setCustomer(newOrderDTO.getCustomer());
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    public boolean existsByOrderId(int id) {
        return orderRepository.existsById(id);
    }

    public void deleteByOrderId(int id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrdersCar(Customer customer, Car car) {
        return orderRepository.findByCar(car);
    }
}
