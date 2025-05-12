package my.kaytmb.dormitory.rest;


import my.kaytmb.dormitory.dto.DormitoryRequest;
import my.kaytmb.dormitory.entity.Dormitory;
import my.kaytmb.dormitory.entity.Room;
import my.kaytmb.dormitory.entity.University;
import my.kaytmb.dormitory.service.DormitoryService;
import my.kaytmb.dormitory.service.UniversityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Controller
@RequestMapping("/api/dormitories")
public class DormitoryController {

    private final DormitoryService dormitoryService;
    private final UniversityService universityService;

    public DormitoryController(DormitoryService dormitoryService, UniversityService universityService) {
        this.dormitoryService = dormitoryService;
        this.universityService = universityService;
    }

    @GetMapping("/add")
    public String addDormitory(Model model) {
        DormitoryRequest dormitory = new DormitoryRequest();
        model.addAttribute("dormitory", dormitory);
        List<University> universities = universityService.getAllUniversities();
        model.addAttribute("universities", universities);
        return "dormitory/add-dormitory";
    }

    @PostMapping("/add")
    public String addDormitory(@ModelAttribute DormitoryRequest dormitoryRequest, Model model, RedirectAttributes redirectAttributes) {
        try {
            dormitoryService.createDormitory(dormitoryRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Общежитие успешно сохранено!");
            return "redirect:/api/dormitories";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Ошибка при сохранении: " + e.getMessage());
            return "dormitory/add-dormitory";
        }
    }

    @GetMapping("/edit/{id}")
    public String updateDormitory(@PathVariable Integer id, Model model) {
        Dormitory dormitory = dormitoryService.getDormitory(id);
        List<University> universities = universityService.getAllUniversities();
        model.addAttribute("dormitory", dormitory);
        model.addAttribute("universities", universities);
        return "/dormitory/edit-dormitory";
    }

    @PostMapping("/edit")
    public String updateDormitory(@ModelAttribute DormitoryRequest dormitoryRequest, Model model, RedirectAttributes redirectAttributes) {
        try {
            dormitoryService.updateDormitory(dormitoryRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Общежитие успешно сохранено!");
            return "redirect:/api/dormitories";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Ошибка при сохранении: " + e.getMessage());
            return "dormitory/add-dormitory";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDormitory(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        dormitoryService.deleteDormitory(id);
        redirectAttributes.addFlashAttribute("successMessage", "Общежитие удалено!");
        return "redirect:/api/dormitories";
    }

    @GetMapping
    public String getAllDormitories(Model model) {
        List<Dormitory> dormitories = dormitoryService.getAllDormitories();
        model.addAttribute("dormitories", dormitories);
        return "dormitory/root-dormitory";
    }

    @GetMapping("/rooms/{id}")
    public String getRoomNumbers(@PathVariable Integer id, Model model) {
        Dormitory dormitory = dormitoryService.getDormitory(id);
        List<Room> rooms = dormitoryService.getRooms(id);
        model.addAttribute("rooms", rooms);
        model.addAttribute("dormitory", dormitory.getId());
        return "dormitory/list-rooms";
    }

}
