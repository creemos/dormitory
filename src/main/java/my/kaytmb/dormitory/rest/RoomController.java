package my.kaytmb.dormitory.rest;


import jakarta.websocket.server.PathParam;
import my.kaytmb.dormitory.dto.RoomRequest;
import my.kaytmb.dormitory.dto.StudentRequest;
import my.kaytmb.dormitory.entity.Dormitory;
import my.kaytmb.dormitory.entity.Room;
import my.kaytmb.dormitory.entity.Student;
import my.kaytmb.dormitory.service.RoomService;
import my.kaytmb.dormitory.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;


/**
 * @author kay 25.03.2025
 */
@Controller
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;
    private final StudentService studentService;

    public RoomController(RoomService roomService, StudentService studentService) {
        this.roomService = roomService;
        this.studentService = studentService;
    }

    @GetMapping("/add")
    public String addRoom(@PathParam("dormitoryId") Integer dormitoryId, Model model) {
        RoomRequest room = new RoomRequest();
        Dormitory dormitory = roomService.getDormitory(dormitoryId);
        room.setDormitoryId(dormitoryId);
        model.addAttribute("room", room);
        model.addAttribute("university", dormitory.getUniversity());
        return "room/add-room";
    }

    @PostMapping("/add")
    public String addRoom(@ModelAttribute RoomRequest roomRequest, Model model, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        try {
            roomService.createRoom(roomRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Комната успешно добавлена!");
            return "redirect:/api/dormitories/rooms/" + roomRequest.getDormitoryId();
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Ошибка при добавлении: " + e.getMessage());
            return "room/add-room";
        }
    }

    @GetMapping("/add-student")
    public String addStudent(@PathParam("roomId") Integer roomId, Model model) {
        Room room = roomService.getRoom(roomId);
        Map<Integer, String> students = studentService.getAvailableStudents(room.getDormitory().getUniversity().getId());
        StudentRequest student = new StudentRequest();
        model.addAttribute("student", student);
        model.addAttribute("students", students);
        return "room/add-student";
    }

    @GetMapping
    public String getRoomByNumberAndDormitory(@PathParam("number") Integer number, @PathParam("dormitoryId") Integer dormitoryId, Model model) {
        Room room = roomService.findByNumberAndDormitoryId(number, dormitoryId);
        model.addAttribute("room", room);
        Dormitory dormitory = roomService.getDormitory(dormitoryId);
        model.addAttribute("dormitory", dormitory.getNumber());
        model.addAttribute("university", dormitory.getUniversity().getName());
        if (room.getId() != null) {
            List<Student> students = roomService.getStudentsByRoom(room.getId());
            model.addAttribute("students", students);
        }
        return "room/room";
    }

    @GetMapping("/exclude")
    public String excludeStudent(@PathParam("studentId") Integer studentId, @PathParam("roomId") Integer roomId, RedirectAttributes redirectAttributes) {
        Student student = studentService.excludePatient(studentId);
        redirectAttributes.addFlashAttribute("successMessage", "Студент выселен!");
        redirectAttributes.addAttribute("number", student.getRoom().getNumber());
        redirectAttributes.addAttribute("dormitoryId", student.getRoom().getDormitory().getId());
        return "redirect:/api/rooms" ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room is deleted, ID: " + id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

}
