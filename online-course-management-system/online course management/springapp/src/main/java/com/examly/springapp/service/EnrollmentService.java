package com.examly.springapp.service;

import com.examly.springapp.model.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment saveEnrollment(Enrollment enrollment);
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(Long id);
    Enrollment updateEnrollment(Long id, Enrollment enrollment);
    void deleteEnrollment(Long id);
}
