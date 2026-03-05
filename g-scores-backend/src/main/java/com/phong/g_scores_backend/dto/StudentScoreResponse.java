package com.phong.g_scores_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentScoreResponse {
    private String sbd;
    private String maNgoaiNgu;
    private Map<String, BigDecimal> scores;
}