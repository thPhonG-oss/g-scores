package com.phong.g_scores_backend.service;

import com.phong.g_scores_backend.dto.ScoreStatisticsResponse;
import com.phong.g_scores_backend.dto.TopStudentResponse;
import com.phong.g_scores_backend.entity.Subject;
import com.phong.g_scores_backend.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreService {
    private final StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<ScoreStatisticsResponse> getScoreStatistics() {
        List<ScoreStatisticsResponse> responses = new ArrayList<>();

        for (Subject subject : Subject.values()) {
            String col = subject.getColumnName();

            // Column name từ enum, không phải user input → an toàn
            String sql = "SELECT " +
                    "COUNT(CASE WHEN " + col + " >= 8 THEN 1 END), " +
                    "COUNT(CASE WHEN " + col + " >= 6 AND " + col + " < 8 THEN 1 END), " +
                    "COUNT(CASE WHEN " + col + " >= 4 AND " + col + " < 6 THEN 1 END), " +
                    "COUNT(CASE WHEN " + col + " < 4 THEN 1 END) " +
                    "FROM students WHERE " + col + " IS NOT NULL";

            Object[] stats = (Object[]) entityManager.createNativeQuery(sql).getSingleResult();

            responses.add(ScoreStatisticsResponse.builder()
                    .subject(subject.getDisplayName())
                    .excellent(((Number) stats[0]).intValue())
                    .good(((Number) stats[1]).intValue())
                    .average(((Number) stats[2]).intValue())
                    .poor(((Number) stats[3]).intValue())
                    .build());
        }

        return responses;
    }

    public List<TopStudentResponse> getTopGroupA() {
        return studentRepository.findTopGroupA().stream()
                .map(s -> TopStudentResponse.builder()
                        .sbd(s.getSbd())
                        .toan(s.getToan())
                        .vatLi(s.getVatLi())
                        .hoaHoc(s.getHoaHoc())
                        .totalScore(s.getToan().add(s.getVatLi()).add(s.getHoaHoc()))
                        .build())
                .toList();
    }
}
