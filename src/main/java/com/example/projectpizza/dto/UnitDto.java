package com.example.projectpizza.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UnitDto {
    private Integer id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;
}
