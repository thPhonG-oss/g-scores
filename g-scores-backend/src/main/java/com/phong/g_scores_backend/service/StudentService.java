package com.phong.g_scores_backend.service;

import com.phong.g_scores_backend.dto.StudentScoreResponse;
import com.phong.g_scores_backend.entity.Student;
import com.phong.g_scores_backend.entity.Subject;
import com.phong.g_scores_backend.exception.BusinessException;
import com.phong.g_scores_backend.exception.ErrorCode;
import com.phong.g_scores_backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentScoreResponse getStudentScores(String sbd) {
        Student student = studentRepository.findById(sbd)
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        Map<String, BigDecimal> scores = new LinkedHashMap<>();
        for (Subject subject : Subject.values()) {
            BigDecimal score = student.getScoreBySubject(subject);
            if (score != null) {
                scores.put(subject.getDisplayName(), score);
            }
        }

        return StudentScoreResponse.builder()
                .sbd(student.getSbd())
                .maNgoaiNgu(student.getMaNgoaiNgu())
                .scores(scores)
                .build();
    }
}
