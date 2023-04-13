package com.example.projectpizza.service;

import com.example.projectpizza.model.DishType;
import com.example.projectpizza.repository.DishTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DishTypeService {
    private final DishTypeRepository dishTypeRepository;

    @Autowired
    public DishTypeService(DishTypeRepository dishTypeRepository) {
        this.dishTypeRepository = dishTypeRepository;
    }

    public List<DishType> findAll() {
        return dishTypeRepository.findAll();
    }

    public DishType findOne(int id) {
        return dishTypeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(DishType DishType) {
        dishTypeRepository.save(DishType);
    }

    @Transactional
    public void update(int id, DishType updatedDishType) {
        updatedDishType.setId(id);
        dishTypeRepository.save(updatedDishType);
    }

    @Transactional
    public void delete(int id) {
        dishTypeRepository.deleteById(id);
    }
}

