package com.pweb1.clinifyback.repository;

import com.pweb1.clinifyback.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
