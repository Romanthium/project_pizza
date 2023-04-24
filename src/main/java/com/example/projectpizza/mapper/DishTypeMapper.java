package com.example.projectpizza.mapper;

import com.example.projectpizza.dto.DishTypeDto;
import com.example.projectpizza.model.DishType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishTypeMapper {

    DishTypeDto toDto(DishType dishType);

    DishType toEntity(DishTypeDto dishTypeDto);

    List<DishTypeDto> toDto(List<DishType> dishTypes);
}
