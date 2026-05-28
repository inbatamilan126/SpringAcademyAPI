package com.academy.app.service;

import com.academy.app.domain.ClassSession;
import com.academy.app.domain.User;
import com.academy.app.exception.BusinessValidationException;
import com.academy.app.exception.ResourceNotFoundException;
import com.academy.app.mapper.ClassSessionMapper;
import com.academy.app.repository.ClassSessionRepository;
import com.academy.app.repository.UserRepository;
import com.academy.dto.ClassSessionCreateRequest;
import com.academy.dto.ClassSessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassSessionService {

    private final ClassSessionRepository classSessionRepository;
    private final UserRepository userRepository;
    private final ClassSessionMapper classSessionMapper;

    @Transactional(readOnly = true)
    public List<ClassSessionDTO> getAllClasses() {
        return classSessionRepository.findAll().stream()
                .map(classSessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClassSessionDTO createClass(ClassSessionCreateRequest request) {
        User instructor = userRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));
        ClassSession classSession = classSessionMapper.toEntity(request);
        classSession.setInstructor(instructor);
        ClassSession savedSession = classSessionRepository.save(classSession);
        return classSessionMapper.toDto(savedSession);
    }

    @Transactional(readOnly = true)
    public ClassSessionDTO getClassById(Long classId) {
        ClassSession classSession = classSessionRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        return classSessionMapper.toDto(classSession);
    }
}
