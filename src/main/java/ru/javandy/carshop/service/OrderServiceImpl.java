package ru.javandy.carshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javandy.carshop.dto.CarDto;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.exeption.DetailNotFoundException;
import ru.javandy.carshop.exeption.OrderNotFoundException;
import ru.javandy.carshop.mapper.CarMapper;
import ru.javandy.carshop.mapper.DetailMapper;
import ru.javandy.carshop.mapper.OrderMapper;
import ru.javandy.carshop.model.Detail;
import ru.javandy.carshop.model.Order;
import ru.javandy.carshop.repository.OrderRepository;
import ru.javandy.carshop.utils.ExcelOrderCustomer;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final CarMapper carMapper;
  private final DetailMapper detailMapper;
  private final ExcelOrderCustomer excelOrderCustomer;

    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtoS = orderMapper.toDtoList(orderRepository.findAll());
        for (OrderDto orderDto: orderDtoS) {
            double countSumDetails = 0;
            for (DetailDto detailDto:orderDto.getDetails()) {
                detailDto.setSumMoney(detailDto.getAmount() * detailDto.getRetailPrice());
                countSumDetails += detailDto.getSumMoney();
            }
            accountTotalOrderAndPayOrderDto(orderDto, countSumDetails);
        }
        orderDtoS.sort((o1, o2) -> o2.getCreated().compareTo(o1.getCreated()));
        return orderDtoS;
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        orderDto.setCreated(new Date());
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderDto)));
    }

    public List<OrderDto> saveOrders(List<OrderDto> ordersDto) {
        return orderMapper.toDtoList(orderRepository.saveAll(orderMapper.toEntityList(ordersDto)));
    }

    public OrderDto findByOrderId(int id) {
        return orderMapper.toDto(orderRepository.findById(id).orElseThrow(() -> new DetailNotFoundException(id)));
    }

    public OrderDto updateOrderId(OrderDto newOrderDto, int id) {
        DetailDto detailDto = null;
        if (!newOrderDto.getDetails().isEmpty()) {
            detailDto = newOrderDto.getDetails().get(newOrderDto.getDetails().size() - 1);
        }
        DetailDto finalDetailDto = detailDto;
        Detail finalDetail = detailMapper.toEntity(finalDetailDto);
        Order newOrder = orderMapper.toEntity(newOrderDto);

        return orderMapper.toDto(orderRepository.findById(id)
                .map(order -> {
                    order.setCreated(newOrder.getCreated());
                    order.setPrepayment(newOrder.getPrepayment());
                    order.setDelivered(newOrder.isDelivered());
                    order.setCardPayment(newOrder.isCardPayment());
                    order.setNote(newOrder.getNote());
                    order.setCar(newOrder.getCar());

                    if (order.getDetails().size() != orderMapper.toEntity(newOrderDto).getDetails().size()) {
                        order.addDetail(finalDetail);
                    }

                    order.setCustomer(newOrder.getCustomer());
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    public List<OrderDto> getAllOrdersCar(CarDto carDto) {
        return orderMapper.toDtoList(orderRepository.findByCar(carMapper.toEntity(carDto)));
    }

    public OrderDto findByDetail(DetailDto detailDto) {
        return orderMapper.toDto(orderRepository.findByDetails(detailMapper.toEntity(detailDto)));
    }

    public void printOrderId(int id) {
        OrderDto orderDto = findByOrderId(id);
        excelOrderCustomer.writeXLSXFile(orderDto);
    }

    private void accountTotalOrderAndPayOrderDto(OrderDto orderDto, double countSumDetails) {
        orderDto.setTotalOrder(countSumDetails);
        orderDto.setPayOrder(orderDto.getTotalOrder() - orderDto.getPrepayment());
    }
}
