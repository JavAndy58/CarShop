package ru.javandy.carshop.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.exeption.OrderNotFoundException;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = orderRepository.save(toEntity(orderDTO));
        return toDTO(order);
    }

    public OrderDTO findByOrderId(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return toDTO(order);
    }

    public OrderDTO updateOrderId(OrderDTO newOrderDTO, int id) {
        Order newOrder = toEntity(newOrderDTO);
        Order updateOrder = orderRepository.findById(id)
                .map(order -> {
                    order.setCreated(newOrder.getCreated());
                    order.setPrepayment(newOrder.getPrepayment());
                    order.setDelivered(newOrderDTO.isDelivered());
                    order.setCardPayment(newOrderDTO.isCardPayment());
                    order.setNote(newOrderDTO.getNote());
                    order.setCar(newOrder.getCar());
                    order.setDetails(newOrder.getDetails());
                    order.setCustomer(newOrder.getCustomer());
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(id));
        return toDTO(updateOrder);
    }

    public boolean existsByOrderId(int id) {
        return orderRepository.existsById(id);
    }

    public void deleteByOrderId(int id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO toDTO(Order order) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(order, OrderDTO.class);
    }
    private Order toEntity(OrderDTO orderDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(orderDTO, Order.class);
    }
}
