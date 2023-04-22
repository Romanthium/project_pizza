package com.example.projectpizza.repository;

import com.example.projectpizza.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Integer> {
    List<Cafe> findByNameAndIdNot(String name, Integer id);

    List<Cafe> findByName(String name);
}
