package com.academy.app.service;

import com.academy.app.domain.ClassSession;
import com.academy.app.domain.Enrollment;
import com.academy.app.domain.User;
import com.academy.app.exception.BusinessValidationException;
import com.academy.app.exception.ResourceNotFoundException;
import com.academy.app.mapper.EnrollmentMapper;
import com.academy.app.repository.ClassSessionRepository;
import com.academy.app.repository.EnrollmentRepository;
import com.academy.app.repository.UserRepository;
import com.academy.dto.EnrollmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final ClassSessionRepository classSessionRepository;
    private final UserRepository userRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Transactional
    public EnrollmentDTO enrollStudent(Long classId, Long studentId) {
        if (enrollmentRepository.existsByStudentIdAndClassSessionId(studentId, classId)) {
            throw new BusinessValidationException("Student is already enrolled in this class");
        }

        ClassSession classSession = classSessionRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        long currentEnrollements = enrollmentRepository.countByClassSessionIdAndStatus(classId, "ACTIVE");
        if (currentEnrollements >= classSession.getCapacity()) {
            throw new BusinessValidationException("Class is at full capacity");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setClassSession(classSession);
        enrollment.setStatus("ACTIVE");
        enrollment.setEnrollmentDate(LocalDate.now());

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(savedEnrollment);
    }
}
