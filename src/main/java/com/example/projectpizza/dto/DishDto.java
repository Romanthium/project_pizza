package com.example.projectpizza.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class DishDto {

    private Integer id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;

    @Positive(message = "Size can'''t be less than 1")
    @NotNull(message = "Size can'''t be less than 1")
    private Integer size;

    private UnitDto unit;

    @PositiveOrZero(message = "Price can'''t be less than 0")
    @NotNull(message = "Price can'''t be less than 0")
    private Float price;

    private DishTypeDto dishType;

    private List<IngredientDto> ingredients;
}
