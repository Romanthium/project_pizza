package com.example.projectpizza.service;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.model.Unit;
import com.example.projectpizza.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Unit findOne(int id) {
        return unitRepository.findById(id).orElse(null);
    }

    public Optional<Unit> findByName(String name) {
        return unitRepository.findByName(name)
                .stream()
                .filter(c -> c.getName().equals(name))
                .findAny();
    }

    public Optional<Unit> findByNameAndIdNot(String name, Integer id) {
        return unitRepository.findByNameAndIdNot(name, id)
                .stream()
                .filter(u -> (u.getName().equals(name) && !(u.getId().equals(id))))
                .findAny();
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
