package com.example.projectpizza.service;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.repository.CafeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CafeServiceTest {

    @Mock
    private CafeRepository cafeRepository;
    @InjectMocks
    private CafeService underTest;

    @Captor
    private ArgumentCaptor<Cafe> cafeArgumentCaptor;

    @Test
    void findAll() {
        underTest.findAll();
        verify(cafeRepository).findAll();
    }

    @Test
    void findAllOrdered() {
        underTest.findAllOrdered();
        verify(cafeRepository).findAllByOrderByNameAsc();
    }

    @Test
    void findAllByManagerId() {
        underTest.findAllByManagerId(anyInt());
        verify(cafeRepository).findAllByManagerId(anyInt());
    }

    @Test
    void findByName() {
        underTest.findByName(anyString());
        verify(cafeRepository).findByName(anyString());
    }

    @Test
    void findByNameAndIdNot() {
        underTest.findByNameAndIdNot(anyString(), anyInt());
        verify(cafeRepository).findByNameAndIdNot(anyString(), anyInt());
    }

    @Test
    void findOne() {
        underTest.findOne(anyInt());
        verify(cafeRepository).findById(anyInt());
    }

    @Test
    void save() {
        Cafe cafe = Cafe.builder()
                .name("Cafe")
                .phone("1111")
                .address("Cafe address")
                .build();

        underTest.save(cafe);

        verify(cafeRepository).save(cafeArgumentCaptor.capture());

        assertThat(cafeArgumentCaptor.getValue()).isEqualTo(cafe);
    }

    @Test
    void update() {
        int cafeId = 1;
        Cafe cafe = Cafe.builder()
                .id(cafeId)
                .name("Cafe")
                .phone("1111")
                .address("Cafe address")
                .build();

        underTest.update(cafeId, cafe);

        verify(cafeRepository).save(cafeArgumentCaptor.capture());
        assertThat(cafeArgumentCaptor.getValue()).isEqualTo(cafe);
        assertThat(cafeArgumentCaptor.getValue().getId()).isEqualTo(cafeId);
    }

    @Test
    void delete() {
        underTest.delete(anyInt());
        verify(cafeRepository).deleteById(anyInt());
    }
}