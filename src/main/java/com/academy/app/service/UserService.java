package com.academy.app.service;

import com.academy.app.domain.Enrollment;
import com.academy.app.domain.Progression;
import com.academy.app.domain.User;
import com.academy.app.exception.ResourceNotFoundException;
import com.academy.app.mapper.EnrollmentMapper;
import com.academy.app.mapper.ProgressionMapper;
import com.academy.app.repository.ProgressionRepository;
import com.academy.app.repository.UserRepository;
import com.academy.dto.EnrollmentDTO;
import com.academy.dto.ProgressionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final ProgressionMapper progressionMapper;
    private final ProgressionRepository progressionRepository;

    @Transactional(readOnly = true)
    public List<EnrollmentDTO> getEnrollmentsByStudentId(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Enrollment> enrollments = student.getEnrollments();
        return enrollments.stream()
                .map(enrollmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProgressionDTO> getProgressionsByStudentId(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        List<Progression> progressions = progressionRepository.findByStudentIdOrderByDateAchievedAsc(studentId);
        return progressions.stream()
                .map(progressionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProgressionDTO createProgression(Long studentId, String level) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Progression progression = new Progression();
        progression.setStudent(student);
        progression.setLevelName(level);
        progression.setDateAchieved(LocalDate.now());
        Progression savedProgression = progressionRepository.save(progression);
        return progressionMapper.toDto(savedProgression);
    }
}
