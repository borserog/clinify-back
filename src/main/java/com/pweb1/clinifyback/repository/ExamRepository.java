package com.pweb1.clinifyback.repository;

import java.util.Optional;

import com.pweb1.clinifyback.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query("FROM Exam WHERE code = ?1")
    Optional<Exam> findByExamCode(String examCode);
}