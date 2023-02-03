package ru.javandy.carshop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.DetailDto;
import ru.javandy.carshop.model.Detail;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DetailMapper {

    DetailDto toDto(Detail detail);
    Detail toEntity(DetailDto detailDto);
}
