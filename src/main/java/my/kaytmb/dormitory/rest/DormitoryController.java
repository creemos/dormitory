package my.kaytmb.dormitory.rest;


import my.kaytmb.dormitory.dto.DormitoryRequest;
import my.kaytmb.dormitory.entity.Dormitory;
import my.kaytmb.dormitory.service.DormitoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kay 26.03.2025
 */
@Controller
@RequestMapping("/api/dormitories/")
public class DormitoryController {

    private final DormitoryService dormitoryService;

    public DormitoryController(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getRoomById(@PathVariable Integer id) {
        Dormitory dormitory = dormitoryService.getDormitory(id);
        return ResponseEntity.ok("Dormitory with ID: " + id + ": " + dormitory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDormitory(@RequestBody DormitoryRequest dormitoryRequest) {
        dormitoryService.updateDormitory(dormitoryRequest);
        return ResponseEntity.ok("Dormitory updated, ID: " + dormitoryRequest.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDormitory(@PathVariable Integer id) {
        dormitoryService.deleteDormitory(id);
        return ResponseEntity.ok("Dormitory is deleted, ID: " + id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dormitory>> getAllDormitories() {
        List<Dormitory> dormitories = dormitoryService.getAllDormitories();
        return ResponseEntity.ok(dormitories);
    }

}
