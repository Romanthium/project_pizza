package com.example.projectpizza.mapper;

import com.example.projectpizza.dto.IngredientDto;
import com.example.projectpizza.model.Ingredient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientDto toDto(Ingredient ingredient);

    Ingredient toEntity(IngredientDto ingredientDto);

    List<IngredientDto> toDto(List<Ingredient> ingredients);
}
