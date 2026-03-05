package com.phong.g_scores_backend.repository;

import com.phong.g_scores_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s " +
            "WHERE s.toan IS NOT NULL AND s.vatLi IS NOT NULL AND s.hoaHoc IS NOT NULL " +
            "ORDER BY (s.toan + s.vatLi + s.hoaHoc) DESC " +
            "LIMIT 10")
    List<Student> findTopGroupA();
}
