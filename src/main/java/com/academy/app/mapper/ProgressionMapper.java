package com.academy.app.mapper;

import com.academy.app.domain.Progression;
import com.academy.dto.ProgressionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgressionMapper {

    @Mapping(source = "id", target = "progressionId")
    ProgressionDTO toDto(Progression progression);
}
