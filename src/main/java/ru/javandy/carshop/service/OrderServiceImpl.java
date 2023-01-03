package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.dto.DetailDTO;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.exeption.OrderNotFoundException;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.mapper.DetailMapper;
import ru.javandy.carshop.mapper.OrderMapper;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.OrderRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CarMapper carMapper;
    private final DetailMapper detailMapper;

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orderDTOS = orderMapper.toDTOList(orderRepository.findAll());
        for (OrderDTO orderDTO:orderDTOS) {
            double countSumDetails = 0;
            for (DetailDTO detailDTO:orderDTO.getDetails()) {
                detailDTO.setSumMoney(detailDTO.getAmount() * detailDTO.getRetailPrice());
                countSumDetails += detailDTO.getSumMoney();
            }
            accountTotalOrderAndPayOrderDTO(orderDTO, countSumDetails);
        }
        return orderDTOS;
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        orderDTO.setCreated(new Date());
        return orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));
    }

    public List<OrderDTO> saveOrders(List<OrderDTO> ordersDTO) {
        return orderMapper.toDTOList(orderRepository.saveAll(orderMapper.toEntityList(ordersDTO)));
    }

    public OrderDTO findByOrderId(int id) {
        return orderMapper.toDTO(orderRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id)));
    }

    public OrderDTO updateOrderId(OrderDTO newOrderDTO, int id) {
        DetailDTO detailDTO = null;
        if (!newOrderDTO.getDetails().isEmpty()) {
            detailDTO = newOrderDTO.getDetails().get(newOrderDTO.getDetails().size() - 1);
        }
        DetailDTO finalDetailDTO = detailDTO;
        Detail finalDetail = detailMapper.toEntity(finalDetailDTO);
        Order newOrder = orderMapper.toEntity(newOrderDTO);

        return orderMapper.toDTO(orderRepository.findById(id)
                .map(order -> {
                    order.setCreated(newOrder.getCreated());
                    order.setPrepayment(newOrder.getPrepayment());
                    order.setDelivered(newOrder.isDelivered());
                    order.setCardPayment(newOrder.isCardPayment());
                    order.setNote(newOrder.getNote());
                    order.setCar(newOrder.getCar());

                    if (order.getDetails().size() != orderMapper.toEntity(newOrderDTO).getDetails().size()) {
                        order.addDetail(finalDetail);
                    }

                    order.setCustomer(newOrder.getCustomer());
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    public boolean existsByOrderId(int id) {
        return orderRepository.existsById(id);
    }

    public void deleteByOrderId(int id) {
        orderRepository.deleteById(id);
    }

    public List<OrderDTO> getAllOrdersCar(CarDTO carDTO) {
        return orderMapper.toDTOList(orderRepository.findByCar(carMapper.toEntity(carDTO)));
    }

    public OrderDTO findByDetail(DetailDTO detailDTO) {
        return orderMapper.toDTO(orderRepository.findByDetails(detailMapper.toEntity(detailDTO)));
    }

    private void accountTotalOrderAndPayOrderDTO(OrderDTO orderDTO, double countSumDetails) {
        orderDTO.setTotalOrder(countSumDetails);
        orderDTO.setPayOrder(orderDTO.getTotalOrder() - orderDTO.getPrepayment());
    }
}
