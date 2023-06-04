package com.example.projectpizza.service;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {

    List<T> findAll();

    List<T> findAllOrdered();

    Optional<T> findByName(String name);

    Optional<T> findByNameAndIdNot(String name, Integer id);

    T findOne(int id);

    void save(T entity);

    void update(int id, T updatedEntity);

    void delete(int id);
}
