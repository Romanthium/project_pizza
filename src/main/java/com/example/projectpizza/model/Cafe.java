package com.example.projectpizza.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "cafe")
public class Cafe extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{name.required}")
    private String name;

    @NotBlank(message = "{phone.required}")
    @Pattern(regexp = "^\\d+$", message = "{only_digits.required}")
    private String phone;
    @NotBlank(message = "{address.required}")
    private String address;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    public Cafe(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
