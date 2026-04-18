package com.examly.springapp.controller;

import com.examly.springapp.model.Instructor;
import com.examly.springapp.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    
    @Autowired
    private InstructorService instructorService;
    
    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        if (instructor == null) {
            return ResponseEntity.badRequest().build();
        }
        Instructor saved = instructorService.saveInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        if (instructors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(instructors);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        Instructor instructor = instructorService.getInstructorById(id);
        if (instructor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(instructor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        Instructor updated = instructorService.updateInstructor(id, instructor);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<Instructor>> getInstructorsPaginated(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Instructor> instructors = instructorService.getInstructorsPaginated(pageable);
        return ResponseEntity.ok(instructors);
    }
    
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<Object> getInstructorsBySpecialization(@PathVariable String specialization) {
        List<Instructor> instructors = instructorService.getInstructorsBySpecialization(specialization);
        if (instructors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No instructors found with specialization: " + specialization);
        }
        return ResponseEntity.ok(instructors);
    }
}