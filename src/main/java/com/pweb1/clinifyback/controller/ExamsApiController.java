package com.pweb1.clinifyback.controller;

import java.util.List;

import com.pweb1.clinifyback.exception.ResourceNotFoundException;
import com.pweb1.clinifyback.model.Exam;
import com.pweb1.clinifyback.model.Patient;
import com.pweb1.clinifyback.repository.ExamRepository;
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
@RequestMapping("/api/exams")
public class ExamsApiController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("")
    public List<Exam> getAllPatients() {
        return examRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(value = "id") Long examId) throws ResourceNotFoundException {
        try {
            Exam exam = examRepository.findById(examId).orElseThrow(() -> 
            new ResourceNotFoundException("Exame não foi encontrado")
            );
            return ResponseEntity.ok().body(exam);
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }        
    }

    @PostMapping("")
    public Exam createExam(@RequestBody Exam exam) { 
        // TODO implement a ExamRequest class to deal with patient id     
        
        Exam newExam = new Exam();
        newExam.setFinished(exam.finished);
        newExam.setDate(exam.date);
        newExam.setCheckIn(exam.checkIn);
        newExam.setCode();

        try {
            Patient patient = patientRepository.findById(exam.patient.id).orElseThrow(() ->
                new ResourceNotFoundException("No patient")
            );
            newExam.patient = patient;
        } catch (Exception ex) {
            System.out.println(ex);
        }       
 
        return examRepository.save(exam);
    }
    
}