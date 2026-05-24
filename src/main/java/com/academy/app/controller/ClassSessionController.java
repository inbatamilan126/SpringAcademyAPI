package com.academy.app.controller;

import com.academy.api.ClassSessionsApi;
import com.academy.app.service.ClassSessionService;
import com.academy.dto.ClassSessionCreateRequest;
import com.academy.dto.ClassSessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassSessionController implements ClassSessionsApi {

    private final ClassSessionService classSessionService;

    @Override
    public ResponseEntity<List<ClassSessionDTO>> getAllClasses() {
        return ResponseEntity.ok(classSessionService.getAllClasses());
    }

    @Override
    public ResponseEntity<ClassSessionDTO> createClass(ClassSessionCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classSessionService.createClass(request));
    }
}
