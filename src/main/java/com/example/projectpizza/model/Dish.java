package com.example.projectpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;

    @Min(value = 1, message = "Size can'''t be less than 1")
    private int size;

    //unit
    @ManyToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private Unit unit;

    @Min(value = 0, message = "Price can'''t be less than 1")
    private float price;

    //type
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private DishType dishType;

//    @ManyToMany
//    @JoinTable(name = "dish_ingredient",
//            joinColumns = @JoinColumn(name = "id_dish"),
//            inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
//    private Set<Ingredient> ingredients;

//    @ManyToMany(mappedBy = "dishes")
//    private Set<Cafe> cafes;
}
