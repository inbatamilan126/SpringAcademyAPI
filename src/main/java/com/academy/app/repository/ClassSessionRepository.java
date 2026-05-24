package com.academy.app.repository;

import com.academy.app.domain.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {
}
