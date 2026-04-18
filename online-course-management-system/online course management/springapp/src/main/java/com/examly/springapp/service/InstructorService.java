package com.examly.springapp.service;

import com.examly.springapp.model.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface InstructorService {
    Instructor saveInstructor(Instructor instructor);
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long id);
    Instructor updateInstructor(Long id, Instructor instructor);
    void deleteInstructor(Long id);
    Page<Instructor> getInstructorsPaginated(Pageable pageable);
    List<Instructor> getInstructorsBySpecialization(String specialization);
}