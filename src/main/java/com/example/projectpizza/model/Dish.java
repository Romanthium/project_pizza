package com.example.projectpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;

    @Positive(message = "Size can'''t be less than 1")
    @NotNull(message = "Size can'''t be less than 1")
    @Digits(message = "Can be only digits", integer = 10, fraction = 0)
    private Integer size;

    //unit
    @ManyToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private Unit unit;

//    @PositiveOrZero(message = "Price can'''t be less than 0")
    @NotNull(message = "Price can'''t be less than 0")
    @Digits(message = "Can be only digits: 9 / 9.9 / 9.99", integer = 10, fraction = 2)
    @DecimalMin(message = "Price can'''t be less than 0", value = "0.0")
    private BigDecimal price;

    //type
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private DishType dishType;

    @ManyToMany
    @OrderBy("name")
    @JoinTable(name = "dish_ingredient",
            joinColumns = @JoinColumn(name = "id_dish"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
    private Set<Ingredient> ingredients;

    @ManyToMany(mappedBy = "dishes")
    private Set<Cafe> cafes;

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

    @Override
    public String toString() {
        return name;
    }
}
