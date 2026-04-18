package com.examly.springapp.repository;

import com.examly.springapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByInstructorInstructorId(Long instructorId);
    List<Course> findByLevel(String level);
}