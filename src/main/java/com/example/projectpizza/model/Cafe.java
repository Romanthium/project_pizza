package com.example.projectpizza.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cafe")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name can'''t be empty")
    private String name;

    @NotBlank(message = "Phone can'''t be empty")
    private String phone;
    @NotBlank(message = "Address can'''t be empty")
    private String address;

    @ManyToMany
    @OrderBy("name")
    @JoinTable(name = "cafe_dish",
            joinColumns = @JoinColumn(name = "id_cafe"),
            inverseJoinColumns = @JoinColumn(name = "id_dish"))
    private Set<Dish> dishes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cafe cafe = (Cafe) o;
        return id.equals(cafe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
