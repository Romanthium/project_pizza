package com.example.projectpizza.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
