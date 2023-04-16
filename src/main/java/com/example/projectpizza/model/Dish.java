package com.example.projectpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;

    @Min(value = 1, message = "Size can'''t be less than 1")
    private Integer size;

    //unit
    @ManyToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private Unit unit;

    @Min(value = 0, message = "Price can'''t be less than 1")
    private Float price;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id.equals(dish.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
