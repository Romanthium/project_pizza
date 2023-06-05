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
public class Dish extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{name.required}")
    private String name;

    @Positive(message = "{size.constraint}")
    @NotNull(message = "{size.constraint}")
    @Digits(message = "{only_digits.required}", integer = 10, fraction = 0)
    private Integer size;

    //unit
    @ManyToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private Unit unit;

//    @PositiveOrZero(message = "Price can'''t be less than 0")
    @NotNull(message = "{price.constraint}")
    @Digits(message = "{price_only_digits.constraint}", integer = 10, fraction = 2)
    @DecimalMin(message = "{price.constraint}", value = "0.0")
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
