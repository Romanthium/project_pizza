package com.example.projectpizza.repository;

import com.example.projectpizza.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Unit> findByNameAndIdNot(String name, Integer id);

    List<Unit> findByName(String name);

    List<Unit> findAllByOrderByNameAsc();
}
