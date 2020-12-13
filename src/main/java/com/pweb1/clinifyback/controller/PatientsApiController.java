package com.pweb1.clinifyback.controller;

import java.util.List;

import com.pweb1.clinifyback.exception.ResourceNotFoundException;
import com.pweb1.clinifyback.model.Patient;
import com.pweb1.clinifyback.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientsApiController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(value = "id") Long patientId) throws ResourceNotFoundException {
        try {
            Patient patient = patientRepository.findById(patientId).orElseThrow(() -> 
            new ResourceNotFoundException("Paciente não foi encontrado")
            );
            return ResponseEntity.ok().body(patient);
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }        
    }

    @PostMapping("")
    public Patient createEmployee(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
    
}