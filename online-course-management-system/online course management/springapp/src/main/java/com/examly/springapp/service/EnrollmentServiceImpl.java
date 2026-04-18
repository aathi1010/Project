package com.examly.springapp.service;

import com.examly.springapp.model.Enrollment;
import com.examly.springapp.repository.EnrollmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    
    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepo.save(enrollment);
    }
    
    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }
    
    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepo.findById(id).orElse(null);
    }
    
    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
        enrollment.setEnrollmentId(id);
        return enrollmentRepo.save(enrollment);
    }
    
    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepo.deleteById(id);
    }
}
