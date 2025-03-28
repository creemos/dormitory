package my.kaytmb.dormitory.rest;


import my.kaytmb.dormitory.dto.StudentRequest;
import my.kaytmb.dormitory.entity.Student;
import my.kaytmb.dormitory.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Controller
@RequestMapping("/api/students/")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createStudent(@RequestBody StudentRequest studentRequest) {
        Integer id = studentService.createStudent(studentRequest);
        return ResponseEntity.ok("New student created, ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudent(id);
        return ResponseEntity.ok("Student with ID: " + id + ": " + student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody StudentRequest studentRequest) {
        studentService.updateStudent(studentRequest);
        return ResponseEntity.ok("Student updated, ID: " + studentRequest.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student is deleted, ID: " + id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> rooms = studentService.getAllStudents();
        return ResponseEntity.ok(rooms);
    }

}
