package com.academy.app.mapper;

import com.academy.app.domain.User;
import com.academy.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentDTOMapper {

    @Mapping(source = "id", target = "studentId")
    StudentDTO toDto(User student);
}
