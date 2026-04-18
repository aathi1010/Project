package com.examly.springapp.service;

import com.examly.springapp.model.Course;
import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
    List<Course> getCoursesByInstructor(Long instructorId);
    List<Course> getCoursesByLevel(String level);
}
