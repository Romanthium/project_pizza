package com.example.projectpizza.service;

import com.example.projectpizza.model.UserRole;
import com.example.projectpizza.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    public UserRole fineOne(int id) {
        return userRoleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Transactional
    public void update(int id, UserRole updatedUserRole) {
        updatedUserRole.setId(id);
        userRoleRepository.save(updatedUserRole);
    }

    @Transactional
    public void delete(int id) {
        userRoleRepository.deleteById(id);
    }
}
