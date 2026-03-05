package com.phong.g_scores_backend.controller;

import com.phong.g_scores_backend.dto.ApiResponse;
import com.phong.g_scores_backend.dto.ScoreStatisticsResponse;
import com.phong.g_scores_backend.dto.TopStudentResponse;
import com.phong.g_scores_backend.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping("/statistics")
    public ApiResponse<List<ScoreStatisticsResponse>> getScoreStatistics() {
        return ApiResponse.<List<ScoreStatisticsResponse>>builder()
                .code(200)
                .message("Success")
                .data(scoreService.getScoreStatistics())
                .build();
    }

    @GetMapping("/top-group-a")
    public ApiResponse<List<TopStudentResponse>> getTopGroupA() {
        return ApiResponse.<List<TopStudentResponse>>builder()
                .code(200)
                .message("Success")
                .data(scoreService.getTopGroupA())
                .build();
    }
}