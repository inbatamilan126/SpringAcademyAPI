package com.academy.app.repository;

import com.academy.app.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentIdAndClassSessionId(Long studentId, Long classSessionId);

    boolean existsByStudentIdAndClassSessionIdAndStatus(Long studentId, Long classSessionId, String status);

    Enrollment findEnrollmentByClassSessionIdAndStudentId(Long classSessionId, Long studentId);

    long countByClassSessionIdAndStatus(Long classSessionId, String status);
}
