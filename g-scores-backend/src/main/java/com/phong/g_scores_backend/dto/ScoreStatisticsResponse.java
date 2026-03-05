package com.phong.g_scores_backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreStatisticsResponse {
    private String subject;
    private int excellent;      // >= 8
    private int good;           // >= 6 and < 8
    private int average;        // >= 4 and < 6
    private int poor;           // < 4
}
