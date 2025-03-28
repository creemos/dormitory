package my.kaytmb.dormitory.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import my.kaytmb.dormitory.dto.UserRequest;
import my.kaytmb.dormitory.entity.User;
import my.kaytmb.dormitory.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kay 27.03.2025
 */
@RestController
@RequestMapping("/api/users/")
@Tag(name = "UserController", description = "Операции с пользователями")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        Integer id = userService.createUser(userRequest);
        return ResponseEntity.ok("New user created, ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok("User with ID: " + id + ": " + user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserRequest userRequest) {
        userService.updateUser(userRequest);
        return ResponseEntity.ok("User updated, ID: " + userRequest.getId());
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<String> disableUser(@PathVariable Integer id) {
        userService.disableUser(id);
        return ResponseEntity.ok("User was disabled, ID: " + id);
    }

    @PatchMapping("/enable/{id}")
    public ResponseEntity<String> enableUser(@PathVariable Integer id) {
        userService.enableUser(id);
        return ResponseEntity.ok("User was enabled, ID: " + id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
