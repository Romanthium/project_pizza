package com.example.projectpizza.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DishTypeDto {
    private Integer id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;
}
