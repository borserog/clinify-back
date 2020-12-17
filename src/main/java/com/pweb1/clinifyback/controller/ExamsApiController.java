package com.pweb1.clinifyback.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

import com.pweb1.clinifyback.exception.ResourceNotFoundException;
import com.pweb1.clinifyback.model.Exam;
import com.pweb1.clinifyback.model.Patient;
import com.pweb1.clinifyback.repository.ExamRepository;
import com.pweb1.clinifyback.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @GetMapping("")
    public List<Exam> getAllPatients() {
        return examRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(value = "id") Long examId) throws ResourceNotFoundException {
        try {
            Exam exam = examRepository.findById(examId)
                    .orElseThrow(() -> new ResourceNotFoundException("Exame não foi encontrado"));
            return ResponseEntity.ok().body(exam);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        }
    }

    @PostMapping("")
    public Exam createExam(@RequestBody Exam exam) {
        // TODO implement a ExamRequest class to deal with patient id

        Exam newExam = new Exam();
        newExam.setPatient(exam.patient);
        newExam.setFinished(exam.finished);
        newExam.setDate(exam.date);
        newExam.setCheckIn(exam.checkIn);
        newExam.setCode();

        System.out.println("-------------------------------\n\n\n" + newExam + "\n\n\n-------------------------------");

        return examRepository.save(newExam);
    }

    @PatchMapping("/checkIn")
    public ResponseEntity<Exam> checkInExam(@RequestBody String examCode) throws NoSuchElementException {
        try {
            Exam examToUpdate = examRepository.findByExamCode(examCode).get();
            LocalDate thisDate = LocalDate.now();
            LocalTime thisTime = LocalTime.now();

            examToUpdate.setCheckIn(LocalDateTime.of(thisDate, thisTime));
            examRepository.save(examToUpdate);

            return ResponseEntity.ok().body(examToUpdate);
        } catch (NoSuchElementException ex) {
            throw ex;
        }
        
    }

    @PatchMapping("/finish/{id}")
    public Exam finishExam(@PathVariable("id") Long examId) throws ResourceNotFoundException {
        try {
            Exam examToFinish = examRepository.findById(examId)
                    .orElseThrow(() -> new ResourceNotFoundException("No exam with that id"));

            System.out.println("-------------------------------\n\n\n" + examToFinish + "\n\n\n-------------------------------");

            examToFinish.setFinished(true);
            return examRepository.save(examToFinish);
        } catch (Exception ex) {
            throw ex;
        }
    }

}