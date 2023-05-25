package com.example.projectpizza.repository;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.model.User;
import com.example.projectpizza.model.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CafeRepositoryTest {

    @BeforeAll
    public static void initTest() throws SQLException {
        //for console access
//        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
//                .start();
    }

    @Autowired
    CafeRepository cafeRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findAllByManagerId() {
        User manager = User.builder()
                .login("manager")
                .password("password")
                .userRole(UserRole.CAFE_MANAGER)
                .build();

        entityManager.persist(manager);


        Cafe cafeB = Cafe.builder()
                .name("Cafe B")
                .phone("2222")
                .address("Cafe B address")
                .manager(manager)
                .build();

        Cafe cafeA = Cafe.builder()
                .name("Cafe A")
                .phone("1111")
                .address("Cafe A address")
                .manager(manager)
                .build();

        entityManager.persist(cafeA);
        entityManager.persist(cafeB);

        cafeRepository.save(cafeA);
        cafeRepository.save(cafeB);


        List<Cafe> expectedCafes = List.of(cafeB, cafeA);

        List<Cafe> actualCafes = cafeRepository.findAllByManagerId(manager.getId());

        assertThat(actualCafes).hasSize(expectedCafes.size());

        assertThat(actualCafes.get(0).getManager().getId()).isEqualTo(expectedCafes.get(0).getManager().getId());
        assertThat(actualCafes.get(1).getManager().getId()).isEqualTo(expectedCafes.get(1).getManager().getId());

        //test sorted output: Cafe A is first, Cafe B is second
        assertThat(actualCafes.get(0).getName()).isEqualTo(expectedCafes.get(1).getName());
        assertThat(actualCafes.get(1).getName()).isEqualTo(expectedCafes.get(0).getName());
    }
}