package com.example.projectpizza.repository;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.model.DishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishTypeRepository extends JpaRepository<DishType, Integer> {
    List<DishType> findByNameAndIdNot(String name, Integer id);

    List<DishType> findByName(String name);
}
