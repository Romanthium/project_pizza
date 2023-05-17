package com.example.projectpizza.service;

import com.example.projectpizza.model.Ingredient;
import com.example.projectpizza.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> findAllOrdered() {
        return ingredientRepository.findAllByOrderByNameAsc();
    }

    public Ingredient findOne(int id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public Optional<Ingredient> findByName(String name) {
        return ingredientRepository.findByName(name)
                .stream()
                .filter(c -> c.getName().equals(name))
                .findAny();
    }

    public Optional<Ingredient> findByNameAndIdNot(String name, Integer id) {
        return ingredientRepository.findByNameAndIdNot(name, id)
                .stream()
                .filter(i -> (i.getName().equals(name) && !(i.getId().equals(id))))
                .findAny();
    }

    @Transactional
    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Transactional
    public void update(int id, Ingredient updatedIngredient) {
        updatedIngredient.setId(id);
        ingredientRepository.save(updatedIngredient);
    }

    @Transactional
    public void delete(int id) {
        ingredientRepository.deleteById(id);
    }
}
