package my.kaytmb.dormitory.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import my.kaytmb.dormitory.dto.UserRequest;
import my.kaytmb.dormitory.entity.User;
import my.kaytmb.dormitory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author kay 27.03.2025
 */
@Controller
@RequestMapping("/api/users/")
@Tag(name = "UserController", description = "Операции с пользователями")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/edit-profile")
    public String editProfile(Model model, Authentication authentication) {
        User user = userService.getUser(((User) authentication.getPrincipal()).getId());
        model.addAttribute("user", user);
        return "sec/edit-profile";
    }

    @GetMapping("/edit-profile/{id}")
    public String editProfile(@PathVariable Integer id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "sec/edit-profile";
    }

    @PostMapping("/edit-profile")
    public String saveProfile(@ModelAttribute("user") UserRequest user, Model model, Authentication authentication,
                              RedirectAttributes redirectAttributes) {
        try {
            userService.updateInfo(user);
            redirectAttributes.addFlashAttribute("successMessage", "Профиль успешно сохранён");
            return "redirect:/api/users/users";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "sec/edit-profile";
        }
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "sec/users";
    }

    @GetMapping("/disable/{id}")
    public String disableUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        userService.disableUser(id);
        redirectAttributes.addFlashAttribute("successMessage", "Пользователь заблокирован!");
        return "redirect:/api/users/users";
    }

    @GetMapping("/enable/{id}")
    public String enableUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        userService.enableUser(id);
        redirectAttributes.addFlashAttribute("successMessage", "Пользователь разблокирован!");
        return "redirect:/api/users/users";
    }


}
