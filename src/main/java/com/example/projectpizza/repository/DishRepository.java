package com.example.projectpizza.repository;

import com.example.projectpizza.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findByName(String name);

    List<Dish> findByNameAndIdNot(String name, Integer id);
}
