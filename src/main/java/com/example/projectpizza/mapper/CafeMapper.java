package com.example.projectpizza.mapper;

import com.example.projectpizza.dto.CafeDto;
import com.example.projectpizza.model.Cafe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CafeMapper {

    CafeDto toDto(Cafe cafe);

    Cafe toEntity(CafeDto cafeDto);

    List<CafeDto> toDto(List<Cafe> cafes);
}
