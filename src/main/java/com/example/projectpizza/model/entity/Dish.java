package com.example.projectpizza.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String name;

    private int size;

    //unit
    @ManyToOne
    @JoinColumn(name="unit", referencedColumnName = "id")
    private Unit unit;

    private float price;

    //type
    @ManyToOne
    @JoinColumn(name="type", referencedColumnName = "id")
    private DishType dishType;
}
