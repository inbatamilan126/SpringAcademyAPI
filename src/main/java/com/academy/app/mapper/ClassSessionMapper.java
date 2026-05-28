package com.academy.app.mapper;

import com.academy.app.domain.ClassSession;
import com.academy.dto.ClassSessionCreateRequest;
import com.academy.dto.ClassSessionDTO;
import com.academy.dto.DayOfWeek;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClassSessionMapper {

    @Mapping(source = "instructor.id", target = "instructorId")
    ClassSessionDTO toDto(ClassSession classSession);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    ClassSession toEntity(ClassSessionCreateRequest request);

    default List<DayOfWeek> setToList(Set<java.time.DayOfWeek> days) {
        if (days == null) return null;
        return days.stream()
                .map(day -> DayOfWeek.valueOf(day.name()))
                .toList();
    }

    default Set<java.time.DayOfWeek> listToSet(List<DayOfWeek> days) {
        if (days == null) return null;
        return days.stream()
                .map(day -> java.time.DayOfWeek.valueOf(day.name()))
                .collect(Collectors.toSet());
    }
}
