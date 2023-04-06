package com.example.projectpizza.service;

import com.example.projectpizza.model.Unit;
import com.example.projectpizza.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UnitService {
    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    public Unit fineOne(int id) {
        return unitRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Unit unit) {
        unitRepository.save(unit);
    }

    @Transactional
    public void update(int id, Unit updatedUnit) {
        updatedUnit.setId(id);
        unitRepository.save(updatedUnit);
    }

    @Transactional
    public void delete(int id) {
        unitRepository.deleteById(id);
    }
}
