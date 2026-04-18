package com.examly.springapp.service;

import com.examly.springapp.model.Course;
import com.examly.springapp.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    private CourseRepo courseRepo;
    
    @Override
    public Course saveCourse(Course course) {
        return courseRepo.save(course);
    }
    
    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
    
    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id).orElse(null);
    }
    
    @Override
    public Course updateCourse(Long id, Course course) {
        course.setCourseId(id);
        return courseRepo.save(course);
    }
    
    @Override
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }
    
    @Override
    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepo.findByInstructorInstructorId(instructorId);
    }
    
    @Override
    public List<Course> getCoursesByLevel(String level) {
        return courseRepo.findByLevel(level);
    }
}
