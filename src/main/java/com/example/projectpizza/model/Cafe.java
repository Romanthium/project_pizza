package com.example.projectpizza.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String name;
    @Min(value = 0)
    private int phone;
    @NotEmpty
    private String address;

    @ManyToMany
    @JoinTable(name = "cafe_dish",
            joinColumns = @JoinColumn(name = "id_cafe"),
            inverseJoinColumns = @JoinColumn(name = "id_dish"))
    private Set<Ingredient> ingredients;
}
