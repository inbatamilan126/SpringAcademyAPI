package com.academy.app.repository;

import com.academy.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT e.student from Enrollment e " +
            "WHERE e.classSession.id = :classId " +
            "AND e.status = :status"
    )
    List<User> findStudentsByClassIdAndStatus(
            @Param("classId") Long classId,
            @Param("status") String status
    );
}
