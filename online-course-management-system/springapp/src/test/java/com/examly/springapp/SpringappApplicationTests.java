package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

    // DAY 3
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void Day3_test_Controller_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller").isDirectory());
    }

    @Test
    @Order(2)
    void Day3_test_Model_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model").isDirectory());
    }

    @Test
    @Order(3)
    void Day3_test_Service_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service").isDirectory());
    }

    @Test
    @Order(4)
    void Day3_test_Repository_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository").isDirectory());
    }

    // Day 4 Testcases

    @Test
    @Order(5)
    void Day4_test_InstructorModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Instructor.java").isFile());
    }

    @Test
    @Order(6)
    void Day4_test_CourseModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Course.java").isFile());
    }

    @Test
    @Order(7)
    void Day4_test_Module_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Module.java").isFile());
    }

    @Test
    @Order(8)
    void Day4_test_Student_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Student.java").isFile());
    }

    @Test
    @Order(9)
    void Day4_test_Enrollment_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Enrollment.java").isFile());
    }

    @Test
    @Order(10)
    void Day4_test_Course_Has_Entity_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Course");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");

            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Entity annotation is missing on Course class");

        } catch (ClassNotFoundException e) {
            fail("❌ Course class not found.");
        } catch (Exception e) {
            fail("❌ Unable to check @Entity annotation on Course.");
        }
    }

    @Test
    @Order(11)
    void test_Course_Has_Id_Annotation_On_Field() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Course");
            Class<?> idAnnotation = Class.forName("jakarta.persistence.Id");

            boolean found = false;

            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent((Class<? extends Annotation>) idAnnotation)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No field in Course class is annotated with @Id");

        } catch (ClassNotFoundException e) {
            fail("❌ Course class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Id annotation in Course class.");
        }
    }

    // Day 5 Testcases
    @Test
    @Order(12)
    void Day5_testInstructorRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/InstructorRepo.java").isFile());
    }

    @Test
    @Order(13)
    void Day5_testCourseRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/CourseRepo.java").isFile());
    }

    @Test
    @Order(14)
    void Day5_testModuleRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/ModuleRepo.java").isFile());
    }

    @Test
    @Order(15)
    void Day5_testStudentRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/StudentRepo.java").isFile());
    }

    @Test
    @Order(16)
    void Day5_testEnrollmentRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/EnrollmentRepo.java").isFile());
    }

    @Test
    @Order(17)
    void Day5_test_CourseRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.CourseRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");

            assertTrue(
                    clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on CourseRepo class");

        } catch (ClassNotFoundException e) {
            fail("❌ CourseRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on CourseRepo.");
        }
    }

    @Test
    @Order(18)
    void Day5_test_InstructorRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.InstructorRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");

            assertTrue(
                    clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on InstructorRepo class");

        } catch (ClassNotFoundException e) {
            fail("❌ InstructorRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on InstructorRepo.");
        }
    }

    @Test
    @Order(19)
    void Day5_test_ModuleRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.ModuleRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");

            assertTrue(
                    clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on ModuleRepo class");

        } catch (ClassNotFoundException e) {
            fail("❌ ModuleRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on ModuleRepo.");
        }
    }

    @Test
    @Order(20)
    void Day5_test_StudentRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.StudentRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");

            assertTrue(
                    clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on StudentRepo class");

        } catch (ClassNotFoundException e) {
            fail("❌ StudentRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on StudentRepo.");
        }
    }

    @Test
    @Order(21)
    void Day5_test_EnrollmentRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.EnrollmentRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");

            assertTrue(
                    clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @Repository annotation is missing on EnrollmentRepo class");

        } catch (ClassNotFoundException e) {
            fail("❌ EnrollmentRepo class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @Repository annotation on EnrollmentRepo.");
        }
    }

    @Test
    @Order(22)
    void Day6_test_InstructorController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/InstructorController.java").isFile());
    }

    @Test
    @Order(23)
    void Day6_test_CourseController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/CourseController.java").isFile());
    }

    @Test
    @Order(24)
    void Day6_test_ModuleController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/ModuleController.java").isFile());
    }

    @Test
    @Order(25)
    void Day6_test_StudentController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/StudentController.java").isFile());
    }

    @Test
    @Order(26)
    void Day6_test_EnrollmentController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/EnrollmentController.java").isFile());
    }

    @Test
    @Order(27)
    void Day6_test_InstructorController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.InstructorController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");

            assertTrue(
                    clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @RestController annotation is missing on InstructorController class");

        } catch (ClassNotFoundException e) {
            fail("❌ InstructorController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RestController annotation on InstructorController.");
        }
    }

    @Test
    @Order(28)
    void Day6_test_CourseController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.CourseController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");

            assertTrue(
                    clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                    "❌ @RestController annotation is missing on CourseController class");

        } catch (ClassNotFoundException e) {
            fail("❌ CourseController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RestController annotation on CourseController.");
        }
    }

    @Test
    @Order(29)
    void Day6_test_InstructorController_Has_PostMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.InstructorController");
            Class<?> postMapping = Class.forName("org.springframework.web.bind.annotation.PostMapping");

            boolean found = false;

            for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) postMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No method with @PostMapping found in InstructorController");

        } catch (ClassNotFoundException e) {
            fail("❌ InstructorController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PostMapping annotation in InstructorController.");
        }
    }

    @Test
    @Order(30)
    void Day6_test_InstructorController_Has_GetMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.InstructorController");
            Class<?> getMapping = Class.forName("org.springframework.web.bind.annotation.GetMapping");

            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) getMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No @GetMapping method found in InstructorController");

        } catch (ClassNotFoundException e) {
            fail("❌ InstructorController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @GetMapping in InstructorController.");
        }
    }

    @Test
    @Order(31)
    void Day6_test_InstructorController_Has_PutMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.InstructorController");
            Class<?> putMapping = Class.forName("org.springframework.web.bind.annotation.PutMapping");

            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) putMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No @PutMapping method found in InstructorController");

        } catch (ClassNotFoundException e) {
            fail("❌ InstructorController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PutMapping in InstructorController.");
        }
    }

    @Test
    @Order(32)
    void Day6_test_InstructorController_Has_DeleteMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.InstructorController");
            Class<?> deleteMapping = Class.forName("org.springframework.web.bind.annotation.DeleteMapping");

            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) deleteMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No @DeleteMapping method found in InstructorController");

        } catch (ClassNotFoundException e) {
            fail("❌ InstructorController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @DeleteMapping in InstructorController.");
        }
    }

    @Test
    @Order(33)
    public void Day6_testCreateInstructor_NoBody_StatusBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/instructors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(34)
    public void Day6_testGetAllInstructors_StatusNoContent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @Order(35)
    void Day7_test_InstructorController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.InstructorController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

            boolean found = false;

            if (clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
            }

            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No @RequestMapping found on InstructorController (class or methods)");

        } catch (ClassNotFoundException e) {
            fail("❌ InstructorController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RequestMapping in InstructorController.");
        }
    }

    @Test
    @Order(36)
    void Day7_test_InstructorController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.InstructorController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");

            boolean found = false;

            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class<? extends Annotation>) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found)
                    break;
            }

            assertTrue(found, "❌ No @PathVariable annotation found in any method parameter of InstructorController");

        } catch (ClassNotFoundException e) {
            fail("❌ InstructorController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PathVariable in InstructorController.");
        }
    }

    @Test
    @Order(37)
    void Day7_test_CourseController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.CourseController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

            boolean found = false;

            if (clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
            }

            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No @RequestMapping found on CourseController (class or methods)");

        } catch (ClassNotFoundException e) {
            fail("❌ CourseController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RequestMapping in CourseController.");
        }
    }

    @Test
    @Order(38)
    void Day7_test_StudentController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.StudentController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

            boolean found = false;

            if (clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
            }

            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No @RequestMapping found on StudentController (class or methods)");

        } catch (ClassNotFoundException e) {
            fail("❌ StudentController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RequestMapping in StudentController.");
        }
    }

    @Test
    @Order(39)
    void Day7_test_CourseController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.CourseController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");

            boolean found = false;

            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class<? extends Annotation>) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found)
                    break;
            }

            assertTrue(found, "❌ No @PathVariable found in any method parameter of CourseController");

        } catch (ClassNotFoundException e) {
            fail("❌ CourseController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PathVariable in CourseController.");
        }
    }

    @Test
    @Order(40)
    void Day7_test_StudentController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.StudentController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");

            boolean found = false;

            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class<? extends Annotation>) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found)
                    break;
            }

            assertTrue(found, "❌ No @PathVariable found in any method parameter of StudentController");

        } catch (ClassNotFoundException e) {
            fail("❌ StudentController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @PathVariable in StudentController.");
        }
    }

    @Test
    @Order(41)
    void Day7_test_EnrollmentController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.EnrollmentController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");

            boolean found = false;

            if (clazz.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                found = true;
            }

            for (Method m : clazz.getDeclaredMethods()) {
                if (m.isAnnotationPresent((Class<? extends Annotation>) requestMapping)) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "❌ No @RequestMapping found on EnrollmentController (class or methods)");

        } catch (ClassNotFoundException e) {
            fail("❌ EnrollmentController class not found.");
        } catch (Exception e) {
            fail("❌ Unable to verify @RequestMapping in EnrollmentController.");
        }
    }

    @Test
    @Order(42)
    void Day8_test_InstructorService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/InstructorService.java").isFile());
    }

    @Test
    @Order(43)
    void Day8_test_CourseService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/CourseService.java").isFile());
    }

    @Test
    @Order(44)
    void Day8_test_ModuleService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/ModuleService.java").isFile());
    }

    @Test
    @Order(45)
    void Day8_test_StudentService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/StudentService.java").isFile());
    }

    @Test
    @Order(46)
    void Day8_test_EnrollmentService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/EnrollmentService.java").isFile());
    }

    @Test
    @Order(47)
    void Day8_test_InstructorServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/InstructorServiceImpl.java").isFile());
    }

    @Test
    @Order(48)
    void Day8_test_CourseServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/CourseServiceImpl.java").isFile());
    }

    @Test
    @Order(49)
    void Day8_test_ModuleServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/ModuleServiceImpl.java").isFile());
    }

    @Test
    @Order(50)
    void Day8_test_StudentServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/StudentServiceImpl.java").isFile());
    }

    @Test
    @Order(51)
    void Day8_test_EnrollmentServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/EnrollmentServiceImpl.java").isFile());
    }

    // POST /api/instructors
    @Test
    @Order(52)
    public void Day8_testAddInstructor() throws Exception {
        String requestBody = "{ \"instructorName\": \"Dr. John Smith\", \"email\": \"john@example.com\", \"specialization\": \"Computer Science\", \"phoneNumber\": \"+1-234-567-8900\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/instructors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructorName").value("Dr. John Smith"))
                .andReturn();
    }

    // GET /api/instructors
    @Test
    @Order(53)
    public void Day8_testGetAllInstructors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].instructorName").value("Dr. John Smith"))
                .andReturn();
    }

    // GET /api/instructors/{id}
    @Test
    @Order(54)
    public void Day8_testGetInstructorById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructorName").value("Dr. John Smith"))
                .andReturn();
    }

    // PUT /api/instructors/{id}
    @Test
    @Order(55)
    public void Day8_testUpdateInstructor() throws Exception {
        String requestBody = "{\"instructorName\": \"Dr. John Updated\", \"email\": \"john.updated@example.com\", \"specialization\": \"AI & Machine Learning\", \"phoneNumber\": \"+1-234-567-8900\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/instructors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructorName").value("Dr. John Updated"))
                .andReturn();
    }

    @Test
    @Order(56)
    public void Day9_testPagination_PageNumberApplied() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.pageNumber").value(0));
    }

    @Test
    @Order(57)
    public void Day9_testPagination_PageSizeApplied() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/page/1/10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.pageSize").value(10));
    }

    @Test
    @Order(58)
    public void Day9_testPagination_SortingPresent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.sort").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageable.sort.sorted").isBoolean());
    }

    @Test
    @Order(59)
    public void Day9_testPagination_ContentArrayExists() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray());
    }

    @Test
    @Order(60)
    public void Day9_testPagination_TotalElementsExists() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").exists());
    }

    @Test
    @Order(61)
    public void Day9_testPagination_TotalPagesExists() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/page/0/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").exists());
    }

    @Test
    @Order(62)
    public void Day10_testAddCourse() throws Exception {
        String requestBody = "{ \"courseName\": \"Java Programming\", \"description\": \"Learn Java from basics\", \"duration\": 40, \"price\": 99.99, \"level\": \"Beginner\", \"instructor\": { \"instructorId\": 1 } }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("Java Programming"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Learn Java from basics"))
                .andReturn();
    }

    // GET /api/courses
    @Test
    @Order(63)
    public void Day10_testGetAllCourses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseName").value("Java Programming"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Learn Java from basics"))
                .andReturn();
    }

    // GET /api/courses/{id}
    @Test
    @Order(64)
    public void Day10_testGetCourseById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("Java Programming"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Learn Java from basics"))
                .andReturn();
    }

    // PUT /api/courses/{id}
    @Test
    @Order(65)
    public void Day10_testUpdateCourse() throws Exception {
        String requestBody = "{ \"courseName\": \"Advanced Java\", \"description\": \"Master Java programming\", \"duration\": 60, \"price\": 149.99, \"level\": \"Advanced\", \"instructor\": { \"instructorId\": 1 } }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/courses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName").value("Advanced Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Master Java programming"))
                .andReturn();
    }

    @Test
    @Order(66)
    public void Day10_testAddStudent() throws Exception {
        String requestBody = "{ \"studentName\": \"Alice Johnson\", \"email\": \"alice@example.com\", \"phoneNumber\": \"+1-555-0100\", \"address\": \"123 Main St\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentName").value("Alice Johnson"))
                .andExpect(jsonPath("$.email").value("alice@example.com"))
                .andReturn();
    }

    // GET ALL STUDENTS
    @Test
    @Order(67)
    public void Day10_testGetAllStudents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("alice@example.com"))
                .andReturn();
    }

    // GET STUDENT BY ID
    @Test
    @Order(68)
    public void Day10_testGetStudentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(1))
                .andReturn();
    }

    // UPDATE STUDENT
    @Test
    @Order(69)
    public void Day10_testUpdateStudent() throws Exception {
        String requestBody = "{ \"studentName\": \"Alice Updated\", \"email\": \"alice.updated@example.com\", \"phoneNumber\": \"+1-555-0100\", \"address\": \"456 New St\" }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Alice Updated"))
                .andExpect(jsonPath("$.email").value("alice.updated@example.com"))
                .andReturn();
    }

    @Test
    @Order(70)
    public void Day11_testGetCoursesByInstructor() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/instructor/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseName").value("Advanced Java"))
                .andReturn();
    }

    @Test
    @Order(71)
    public void Day12_testGetCoursesByLevel() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/level/Advanced")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseName").value("Advanced Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].level").value("Advanced"))
                .andReturn();
    }

    @Test
    @Order(72)
    public void Day12_testGetStudentsByEmail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/students/email/alice.updated@example.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentName").value("Alice Updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("alice.updated@example.com"))
                .andReturn();
    }

    @Test
    @Order(73)
    public void Day12_testGetCoursesByLevel_NotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/level/Expert")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().string("No courses found at level: Expert"));
    }

    @Test
    @Order(74)
    public void Day12_testGetInstructorBySpecialization() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/specialization/AI & Machine Learning")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].instructorName").value("Dr. John Updated"))
                .andReturn();
    }

    @Test
    @Order(75)
    public void Day12_testGetInstructorBySpecialization_NotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/instructors/specialization/Biology")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No instructors found with specialization: Biology"));
    }

    @Test
    @Order(76)
    void Day13_test_exception_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/exception").isDirectory());
    }

    @Test
    @Order(77)
    void Day13_test_GlobalException_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/exception/GlobalExceptionHandler.java").isFile());
    }

    @Test
    @Order(78)
    void Day14_test_configuration_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/configuration").isDirectory());
    }

    @Test
    @Order(79)
    public void Day15_testAOPLogFileExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/aop").isDirectory());
    }

}