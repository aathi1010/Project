package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enrollment {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long enrollmentId;
    
    public Long getEnrollmentId() {
        return enrollmentId;
    }
    
    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
}
