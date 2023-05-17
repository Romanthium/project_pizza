package com.example.projectpizza.repository;

import com.example.projectpizza.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByNameAndIdNot(String name, Integer id);

    List<Ingredient> findByName(String name);

    List<Ingredient> findAllByOrderByNameAsc();
}
