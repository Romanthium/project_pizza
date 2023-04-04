package com.example.projectpizza.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @ManyToOne
    @JoinColumn(name="role", referencedColumnName = "id")
    private UserRole userRole;
}
