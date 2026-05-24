package com.academy.app.repository;

import com.academy.app.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentIdAndClassSessionId(Long studentId, Long classSessionId);

    long countByClassSessionIdAndStatus(Long classSessionId, String status);
}
