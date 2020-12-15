package com.pweb1.clinifyback.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.IntStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "t_exam")
public class Exam {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "finished")
    public boolean finished;

    @Column(name = "date")
    public LocalDateTime date;

    @Column(name = "check_in")
    @Nullable
    public LocalDateTime checkIn;

    @Column(name = "code")
    public String code;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    public Patient patient;

    public Exam() {
    }

    public Exam(Long id, boolean finished, LocalDateTime date, LocalDateTime check_in, Patient patient) {
        this.id = id;
        this.finished = finished;
        this.date = date;
        this.checkIn = check_in;
        this.code = generateExamCode();
        this.patient = patient;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getCheckin() {
        return this.checkIn;
    }

    public void setCheckIn(LocalDateTime check_in) {
        this.checkIn = check_in;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode() {
        this.code = generateExamCode();
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Exam id(Long id) {
        this.id = id;
        return this;
    }

    public Exam finished(boolean finished) {
        this.finished = finished;
        return this;
    }

    public Exam date(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public Exam check_in(LocalDateTime checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public Exam code(String code) {
        this.code = code;
        return this;
    }

    public Exam patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    // Demo Only
    private String generateExamCode() {
        int [] asciiUppercase = IntStream.range(65, 91).toArray();
        int [] asciiNumber = IntStream.range(48, 58).toArray();
        StringBuilder result = new StringBuilder();

        for (int i=0; i < 7; i++) {
            if (i <= 4) {
                int randomIndex = (int) (Math.random() * asciiUppercase.length);
                int fetchedAsciiCode = asciiUppercase[randomIndex];
                char asciiChar = (char) fetchedAsciiCode;

                result.append(asciiChar);                
            } else {
                int randomIndex = (int) (Math.random() * asciiNumber.length);
                int fetchedAsciiCode = asciiNumber[randomIndex];
                char asciiChar = (char) fetchedAsciiCode;

                result.append(asciiChar);    
            }

        }
        
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Exam)) {
            return false;
        }
        Exam exam = (Exam) o;
        return Objects.equals(id, exam.id) && finished == exam.finished && Objects.equals(date, exam.date) && Objects.equals(checkIn, exam.checkIn) && Objects.equals(code, exam.code) && Objects.equals(patient, exam.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, finished, date, checkIn, code, patient);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", finished='" + isFinished() + "'" +
            ", date='" + getDate() + "'" +
            ", check_in='" + getCheckin() + "'" +
            ", code='" + getCode() + "'" +
            ", patient='" + getPatient() + "'" +
            "}";
    }
}
