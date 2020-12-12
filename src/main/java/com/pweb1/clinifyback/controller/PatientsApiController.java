package com.pweb1.clinifyback.controller;

import java.util.List;

import com.pweb1.clinifyback.model.Patient;
import com.pweb1.clinifyback.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientsApiController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
}