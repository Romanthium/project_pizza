package com.example.projectpizza.mapper;

import com.example.projectpizza.dto.DishDto;
import com.example.projectpizza.model.Dish;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public interface DishMapper {

    DishDto toDto(Dish dish);

    Dish toEntity(DishDto dishDto);

    List<DishDto> toDto(List<Dish> dishes);

    List<Dish> toEntity(List<DishDto> dishDtos);
}
