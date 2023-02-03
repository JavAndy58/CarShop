package ru.javandy.carshop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.OrderDto;
import ru.javandy.carshop.model.Order;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DetailMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);
    List<Order> toEntityList(List<OrderDto> orderDtos);

}
