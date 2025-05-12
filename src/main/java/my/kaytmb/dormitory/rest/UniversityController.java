package my.kaytmb.dormitory.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.kaytmb.dormitory.dto.UniversityRequest;
import my.kaytmb.dormitory.entity.University;
import my.kaytmb.dormitory.service.UniversityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author kay 26.03.2025
 */
@Controller
@RequestMapping("/api/universities")
@Tag(name = "UniversityController", description = "Операции с университетами")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/add")
    public String createUniversity(Model model) {
        model.addAttribute("university", new UniversityRequest());
        return "university/add-university";
    }

    @Operation(summary = "Создание нового университета", description = "Создает новый университет с данными из запроса")
    @ApiResponse(responseCode = "200", description = "Успешный запрос")
    @PostMapping("/add")
    public String createUniversity(@ModelAttribute UniversityRequest universityRequest, Model model, RedirectAttributes redirectAttributes) {
        try {
            universityService.createUniversity(universityRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Университет успешно добавлен!");
            return "redirect:/api/universities";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Ошибка при добавлении: " + e.getMessage());
            return "universities/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String getUpdateUniversity(@PathVariable Integer id, Model model) {
        University university = universityService.getUniversity(id);
        model.addAttribute("university", university);
        return "university/edit-university";
    }

    @PostMapping("/edit/{id}")
    public String postUpdateUniversity(@ModelAttribute UniversityRequest university, RedirectAttributes redirectAttributes) {
        universityService.updateUniversity(university);
        redirectAttributes.addFlashAttribute("successMessage", "Университет успешно обновлён!");
        return "redirect:/api/universities";
    }

    @GetMapping("/delete/{id}")
    public String deleteUniversity(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        universityService.deleteUniversity(id);
        redirectAttributes.addFlashAttribute("successMessage", "Университет успешно удалён!");
        return "redirect:/api/universities";
    }

    @GetMapping
    public String getAllUniversities(Model model) {
        List<University> universities = universityService.getAllUniversities();
        model.addAttribute("universities", universities);
        return "university/root-university";
    }

    @GetMapping("/json")
    @ResponseBody
    public Map<Integer, String> getAllUniversities() {
        List<University> universities = universityService.getAllUniversities();
        return universities.stream().collect(Collectors.toMap(University::getId, University::getName));
    }


}
