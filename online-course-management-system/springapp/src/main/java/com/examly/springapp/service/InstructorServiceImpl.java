package com.examly.springapp.service;

import com.examly.springapp.model.Instructor;
import com.examly.springapp.repository.InstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    
    @Autowired
    private InstructorRepo instructorRepo;
    
    @Override
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepo.save(instructor);
    }
    
    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }
    
    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepo.findById(id).orElse(null);
    }
    
    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {
        instructor.setInstructorId(id);
        return instructorRepo.save(instructor);
    }
    
    @Override
    public void deleteInstructor(Long id) {
        instructorRepo.deleteById(id);
    }
    
    @Override
    public Page<Instructor> getInstructorsPaginated(Pageable pageable) {
        return instructorRepo.findAll(pageable);
    }
    
    @Override
    public List<Instructor> getInstructorsBySpecialization(String specialization) {
        return instructorRepo.findBySpecialization(specialization);
    }
}