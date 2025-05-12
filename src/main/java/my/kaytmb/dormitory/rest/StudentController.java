package my.kaytmb.dormitory.rest;


import my.kaytmb.dormitory.dto.StudentRequest;
import my.kaytmb.dormitory.entity.Student;
import my.kaytmb.dormitory.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Controller
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/add")
    public String addStudent(Model model) {
        StudentRequest student = new StudentRequest();
        model.addAttribute("student", student);
        return "student/add-student";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute StudentRequest student, Model model, RedirectAttributes redirectAttributes) {
        try {
            studentService.createStudent(student);
            redirectAttributes.addFlashAttribute("successMessage", "Студент успешно создан!");
            return "redirect:/api/students";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Ошибка при добавлении студента: " + e.getMessage());
            return "/student/add-student";
        }
    }


    @GetMapping("/edit/{id}")
    public String updateStudent(@PathVariable Integer id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student/edit-student";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@ModelAttribute StudentRequest student, Model model, RedirectAttributes redirectAttributes) {
        try {
            studentService.updateStudent(student);
            redirectAttributes.addFlashAttribute("successMessage", "Карточка студента обновлена!");
            return "redirect:/api/students";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Произошла ошибка: " + e.getMessage());
            return "student/edit-student";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("successMessage", "Студент успешно удален!");
        return "redirect:/api/students";
    }

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/root-students";
    }

}
