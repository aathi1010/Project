package com.examly.springapp.controller;

import com.examly.springapp.model.Course;
import com.examly.springapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course saved = courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updated = courseService.updateCourse(id, course);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable Long instructorId) {
        List<Course> courses = courseService.getCoursesByInstructor(instructorId);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/level/{level}")
    public ResponseEntity<Object> getCoursesByLevel(@PathVariable String level) {
        List<Course> courses = courseService.getCoursesByLevel(level);
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No courses found at level: " + level);
        }
        return ResponseEntity.ok(courses);
    }
}