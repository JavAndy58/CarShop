package ru.javandy.carshop.service;

import ru.javandy.carshop.dto.CarDTO;
import ru.javandy.carshop.dto.DetailDTO;
import ru.javandy.carshop.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO saveOrder(OrderDTO orderDTO);
    List<OrderDTO> saveOrders(List<OrderDTO> ordersDTO);
    OrderDTO findByOrderId(int id);
    OrderDTO updateOrderId(OrderDTO orderDTO, int id);
    boolean existsByOrderId(int id);
    void deleteByOrderId(int id);
    List<OrderDTO> getAllOrdersCar(CarDTO carDTO);
    OrderDTO findByDetail(DetailDTO detailDTO);
}
