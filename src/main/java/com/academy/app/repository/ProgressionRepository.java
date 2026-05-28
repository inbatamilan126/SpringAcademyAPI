package com.academy.app.repository;

import com.academy.app.domain.Progression;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressionRepository extends JpaRepository<Progression, Long> {

    List<Progression> findByStudentIdOrderByDateAchievedAsc(Long studentId);
}
