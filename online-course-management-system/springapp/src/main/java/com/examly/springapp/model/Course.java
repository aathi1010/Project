package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {
   
     @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long courseId; 
     private String courseName;
     private String description; 
     private int duration; 
     private double price; 
     private String level; 
     
     @ManyToOne 
     @JoinColumn(name = "instructor_id") 
     private Instructor instructor;

     public Course(){}

     public Course(String courseName, String description, int duration, double price, String level,Instructor instructor) {
        this.courseName = courseName;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.level = level;
        this.instructor = instructor;
    }

     public Long getCourseId() {
         return courseId;
     }

     public void setCourseId(Long courseId) {
         this.courseId = courseId;
     }

     public String getCourseName() {
         return courseName;
     }

     public void setCourseName(String courseName) {
         this.courseName = courseName;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public int getDuration() {
         return duration;
     }

     public void setDuration(int duration) {
         this.duration = duration;
     }

     public double getPrice() {
         return price;
     }

     public void setPrice(double price) {
         this.price = price;
     }

     public String getLevel() {
         return level;
     }

     public void setLevel(String level) {
         this.level = level;
     }

     public Instructor getInstructor() {
         return instructor;
     }

     public void setInstructor(Instructor instructor) {
         this.instructor = instructor;
     }
}
