package com.example.projectpizza.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "cafe")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;

    @NotBlank(message = "Phone can'''t be empty")
    private String phone;
    @NotBlank(message = "Address can'''t be empty")
    private String address;

//    @ManyToMany
//    @JoinTable(name = "cafe_dish",
//            joinColumns = @JoinColumn(name = "id_cafe"),
//            inverseJoinColumns = @JoinColumn(name = "id_dish"))
//    private Set<Ingredient> ingredients;
}
