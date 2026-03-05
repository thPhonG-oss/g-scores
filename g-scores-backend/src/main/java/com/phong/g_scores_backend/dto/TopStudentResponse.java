package com.phong.g_scores_backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopStudentResponse {
    private String sbd;
    private BigDecimal toan;
    private BigDecimal vatLi;
    private BigDecimal hoaHoc;
    private BigDecimal totalScore;
}
