package com.pweb1.clinifyback.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "healthPlan")
    public String healthPlan;

    @Column(name = "birth_date")
    public LocalDate birthDate;


    public Patient() {
    }

    public Patient(Long id, String name, String healthPlan, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.healthPlan = healthPlan;
        this.birthDate = birthDate;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealthPlan() {
        return this.healthPlan;
    }

    public void setHealthPlan(String healthPlan) {
        this.healthPlan = healthPlan;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    
}
