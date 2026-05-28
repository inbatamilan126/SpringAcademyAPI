package com.academy.app.controller;

import com.academy.api.UsersApi;
import com.academy.app.service.UserService;
import com.academy.dto.EnrollmentDTO;
import com.academy.dto.ProgressionCreateRequest;
import com.academy.dto.ProgressionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudentId(Long userId) {
        return ResponseEntity.ok(userService.getEnrollmentsByStudentId(userId));
    }

    @Override
    public ResponseEntity<List<ProgressionDTO>> getProgressionsByStudentId(Long userId) {
        return ResponseEntity.ok(userService.getProgressionsByStudentId(userId));
    }

    @Override
    public ResponseEntity<ProgressionDTO> createProgression(Long userId, ProgressionCreateRequest progressionCreateRequest) {
        ProgressionDTO progressionDTO = userService.createProgression(userId, progressionCreateRequest.getLevelName());
        return ResponseEntity.status(HttpStatus.CREATED).body(progressionDTO);
    }
}
