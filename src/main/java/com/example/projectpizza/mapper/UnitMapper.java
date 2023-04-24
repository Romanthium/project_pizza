package com.example.projectpizza.mapper;

import com.example.projectpizza.dto.UnitDto;
import com.example.projectpizza.model.Unit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnitMapper {

    UnitDto toDto(Unit unit);

    Unit toEntity(UnitDto unitDto);

    List<UnitDto> toDto(List<Unit> units);
}
