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

    //        @Query("SELECT u.id as id, " +
//                "u.login as login, " +
//                "u.password as password, " +
//                "u.userRole as role_id " +
//                "FROM User u " +
//                "INNER JOIN u.userRole ur " +
//                "WHERE ur.name = 'ROLE_CAFE_MANAGER'")
    @Query(value = "SELECT user_info.id as id," +
            " user_info.login as login," +
            " user_info.password as password," +
            " user_info.user_role as user_role" +
            " FROM pizza_cafe_db.userinfo as user_info" +
            " WHERE user_role = 'CAFE_MANAGER'" +
            " ORDER BY login",
            nativeQuery = true)
    List<User> findAllManagers();

    Optional<User> findByLoginAndIdNot(String login, Integer id);
}
