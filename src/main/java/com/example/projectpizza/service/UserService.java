package com.example.projectpizza.service;

import com.example.projectpizza.handler.IdNotFoundException;
import com.example.projectpizza.model.User;
import com.example.projectpizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService implements EntityService<User> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllOrdered() {
        return userRepository.findAllByOrderByLoginAsc();
    }

    public List<User> findAllManagers() {
        return userRepository.findAllManagers();
    }

    public  Optional<User> findByName(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> findByNameAndIdNot(String login, Integer id) {
        return userRepository.findByLoginAndIdNot(login, id);
    }

    public User findOne(int id) {
        return userRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "User"));
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
