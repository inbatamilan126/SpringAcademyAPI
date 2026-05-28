package com.academy.app.controller;

import com.academy.api.UsersApi;
import com.academy.app.service.UserService;
import com.academy.dto.EnrollmentDTO;
import lombok.RequiredArgsConstructor;
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
}
