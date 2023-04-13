package com.example.projectpizza.service;

import com.example.projectpizza.model.Dish;
import com.example.projectpizza.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Dish findOne(int id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Dish dish) {
        dishRepository.save(dish);
    }

    @Transactional
    public void update(int id, Dish updatedDish) {
        updatedDish.setId(id);
        dishRepository.save(updatedDish);
    }

    @Transactional
    public void delete(int id) {
        dishRepository.deleteById(id);
    }
}

