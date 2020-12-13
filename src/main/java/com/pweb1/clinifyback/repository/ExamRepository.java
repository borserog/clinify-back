package com.pweb1.clinifyback.repository;

import com.pweb1.clinifyback.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}