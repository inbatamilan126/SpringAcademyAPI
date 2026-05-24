package com.academy.app.mapper;

import com.academy.app.domain.ClassSession;
import com.academy.dto.ClassSessionCreateRequest;
import com.academy.dto.ClassSessionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ClassSessionMapper {

    @Mapping(source = "instructor.id", target = "instructorId")
    ClassSessionDTO toDto(ClassSession classSession);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    ClassSession toEntity(ClassSessionCreateRequest request);
}
