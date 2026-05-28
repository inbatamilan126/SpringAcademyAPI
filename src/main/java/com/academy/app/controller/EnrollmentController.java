package com.academy.app.controller;

import com.academy.api.EnrollmentsApi;
import com.academy.app.service.EnrollmentService;
import com.academy.dto.EnrollmentDTO;
import com.academy.dto.EnrollmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnrollmentController implements EnrollmentsApi {

    private final EnrollmentService enrollmentService;

    @Override
    public ResponseEntity<EnrollmentDTO> enrollStudent(Long classId, EnrollmentRequest request) {
        EnrollmentDTO enrollmentDTO = enrollmentService.enrollStudent(classId, request.getStudentId());
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentDTO);
    }

    @Override
    public ResponseEntity<Void> deleteEnrollmentById(Long enrollmentId) {
        enrollmentService.deleteEnrollmentById(enrollmentId);
        return ResponseEntity.noContent().build();
    }
}
