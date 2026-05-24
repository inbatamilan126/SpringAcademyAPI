package com.academy.app.mapper;

import com.academy.app.domain.Enrollment;
import com.academy.dto.EnrollmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "classSession.id", target = "classSessionId")
    EnrollmentDTO toDto(Enrollment enrollment);
}
