package com.example.projectpizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "dish_type")
public class DishType extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{name.required}")
    private String name;

    @OneToMany(mappedBy = "dishType")
    private List<Dish> dishes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishType dishType = (DishType) o;
        return id.equals(dishType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
