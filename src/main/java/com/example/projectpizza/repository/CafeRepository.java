package com.example.projectpizza.repository;

import com.example.projectpizza.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Integer> {
    List<Cafe> findByNameAndIdNot(String name, Integer id);

    List<Cafe> findByName(String name);

    @Query(value = "SELECT * FROM pizza_cafe_db.cafe as cafe " +
            "WHERE cafe.manager_id = :id " +
            "ORDER BY cafe.name",
            nativeQuery = true)
    List<Cafe> findAllByManagerId(@Param("id") Integer id);

    List<Cafe> findAllByOrderByNameAsc();
}
