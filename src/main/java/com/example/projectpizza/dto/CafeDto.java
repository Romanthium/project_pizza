package com.example.projectpizza.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CafeDto {

    private Integer id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;

    @NotBlank(message = "Phone can'''t be empty")
    private String phone;
    @NotBlank(message = "Address can'''t be empty")
    private String address;

    private List<DishDto> dishes;
}
