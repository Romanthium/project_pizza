package com.example.projectpizza.service;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CafeService {
    private final CafeRepository cafeRepository;

    @Autowired
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    public List<Cafe> findAll() {
        return cafeRepository.findAll();
    }

    public Cafe fineOne(int id) {
        return cafeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Cafe cafe) {
        cafeRepository.save(cafe);
    }

    @Transactional
    public void update(int id, Cafe updatedCafe) {
        updatedCafe.setId(id);
        cafeRepository.save(updatedCafe);
    }

    @Transactional
    public void delete(int id) {
        cafeRepository.deleteById(id);
    }
}
