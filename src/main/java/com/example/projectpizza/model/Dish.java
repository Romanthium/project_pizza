package com.example.projectpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String name;

    private int size;

    //unit
    @ManyToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private Unit unit;

    private float price;

    //type
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private DishType dishType;

    @ManyToMany
    @JoinTable(name = "dish_ingredient",
            joinColumns = @JoinColumn(name="id_dish"),
            inverseJoinColumns = @JoinColumn(name="id_ingredient"))
    private Set<Ingredient> ingredients;

    @ManyToMany(mappedBy = "dishes")
    private Set<Cafe> cafes;
}
