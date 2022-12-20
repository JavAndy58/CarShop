package ru.javandy.carshop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.OrderDTO;
import ru.javandy.carshop.model.Order;

@Mapper(componentModel = "spring", uses = {DetailMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}
