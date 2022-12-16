package ru.javandy.carshop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.javandy.carshop.dto.DetailDTO;
import ru.javandy.carshop.model.Detail;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DetailMapper {

    DetailDTO toDTO(Detail detail);
    Detail toEntity(DetailDTO detailDTO);
}
