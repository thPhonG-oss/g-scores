package com.phong.g_scores_backend.controller;

import com.phong.g_scores_backend.dto.ApiResponse;
import com.phong.g_scores_backend.dto.StudentScoreResponse;
import com.phong.g_scores_backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{sbd}/scores")
    public ApiResponse<StudentScoreResponse> getStudentScore(@PathVariable String sbd) {
        StudentScoreResponse response = studentService.getStudentScores(sbd);
        return ApiResponse.<StudentScoreResponse>builder()
                .code(200)
                .message("Success")
                .data(response)
                .build();
    }
}

