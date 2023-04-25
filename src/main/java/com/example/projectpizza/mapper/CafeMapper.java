package com.example.projectpizza.mapper;

import com.example.projectpizza.dto.CafeDto;
import com.example.projectpizza.model.Cafe;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = DishMapper.class)
public interface CafeMapper {

    CafeDto toDto(Cafe cafe);

    @InheritInverseConfiguration
    Cafe toEntity(CafeDto cafeDto);

    List<CafeDto> toDto(List<Cafe> cafes);
    List<Cafe> toEntity(List<CafeDto> cafeDtos);
}
