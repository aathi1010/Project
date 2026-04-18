package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Instructor { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId; 
    private String instructorName; 
    private String email; 
    private String specialization;
    private String phoneNumber;
    
    public Instructor(){}

    public Instructor(String instructorName, String email, String specialization, String phoneNumber) {
        this.instructorName = instructorName;
        this.email = email;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}    
