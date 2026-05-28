package com.academy.app.service;

import com.academy.app.domain.Enrollment;
import com.academy.app.domain.User;
import com.academy.app.exception.ResourceNotFoundException;
import com.academy.app.mapper.EnrollmentMapper;
import com.academy.app.repository.UserRepository;
import com.academy.dto.EnrollmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EnrollmentMapper enrollmentMapper;

    public List<EnrollmentDTO> getEnrollmentsByStudentId(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Enrollment> enrollments = student.getEnrollments();
        return enrollments.stream()
                .map(enrollmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
