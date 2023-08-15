package com.example.projectpizza.repository;

import com.example.projectpizza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    @Query(value = "SELECT NEW User(u.id, u.login, u.userRole) " +
            "FROM User as u " +
            "WHERE u.userRole = 'CAFE_MANAGER' " +
            "ORDER BY u.login")
    List<User> findAllManagers();

    Optional<User> findByLoginAndIdNot(String login, Integer id);

    List<User> findAllByOrderByLoginAsc();
}
