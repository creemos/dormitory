package my.kaytmb.dormitory.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.kaytmb.dormitory.dto.UniversityRequest;
import my.kaytmb.dormitory.entity.University;
import my.kaytmb.dormitory.service.UniversityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Controller
@RequestMapping("/api/universities/")
@Tag(name = "UniversityController", description = "Операции с университетами")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Operation(summary = "Создание нового университета", description = "Создает новый университет с данными из запроса")
    @ApiResponse(responseCode = "200", description = "Успешный запрос")
    @PostMapping("/add")
    public ResponseEntity<String> createUniversity(@RequestBody UniversityRequest universityRequest) {
        Integer id = universityService.createUniversity(universityRequest);
        return ResponseEntity.ok("New university created, ID: " + id);
    }

    @Operation(summary = "Получение информации о конкретном университете", description = "Получение информации о конкретном университете")
    @ApiResponse(responseCode = "200", description = "Успешный запрос")
    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable Integer id) {
        University university = universityService.getUniversity(id);
        return ResponseEntity.ok(university);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUniversity(@RequestBody UniversityRequest universityRequest) {
        universityService.updateUniversity(universityRequest);
        return ResponseEntity.ok("University updated, ID: " + universityRequest.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUniversity(@PathVariable Integer id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.ok("University is deleted, ID: " + id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<University>> getAllUniversities() {
        List<University> universities = universityService.getAllUniversities();
        return ResponseEntity.ok(universities);
    }


}
