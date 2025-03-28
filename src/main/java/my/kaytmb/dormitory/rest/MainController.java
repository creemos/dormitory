package my.kaytmb.dormitory.rest;


import my.kaytmb.dormitory.dto.UserRequest;
import my.kaytmb.dormitory.entity.User;
import my.kaytmb.dormitory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author kay 26.03.2025
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "sec/login"; // Отображение страницы входа
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        if (authentication == null) {
            model.addAttribute("username", "Гость");
        } else {
            model.addAttribute("username", authentication.getName());
        }
        return "home"; // Отображение страницы входа
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRequest());
        return "sec/register"; // Отображение страницы входа
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRequest user, RedirectAttributes redirectAttributes, Model model) {
        try {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("successMessage", "Регистрация прошла успешно!");
            return "redirect:/sec/register";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "sec/register";
        }
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Authentication authentication,
            Model model
    ) {
        User user = (User) authentication.getPrincipal();
        try {
            if (!userService.isCorrectPassword(currentPassword, user.getPassword())) {
                model.addAttribute("errorMessage", "Текущий пароль введён неверно!");
                model.addAttribute("user", user);
                return "edit-my-profile";
            }
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("errorMessage", "Пароли не совпадают!");
                model.addAttribute("user", user);
                return "edit-my-profile";
            }
            userService.changePassword(user, newPassword);
            model.addAttribute("successMessage", "Пароль успешно изменён!");
            model.addAttribute("user", user);
            return "edit-my-profile";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user);
            return "edit-my-profile";
        }
    }

    @GetMapping("/my-profile")
    public String profile(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "my-profile";
    }

    @GetMapping("/edit-my-profile")
    public String editMyProfile(Model model, Authentication authentication) {
        User user = userService.getUser(((User) authentication.getPrincipal()).getId());
        model.addAttribute("user", user);
        return "edit-my-profile";
    }

    @PostMapping("/edit-my-profile")
    public String saveMyProfile(@ModelAttribute("user") UserRequest user, Model model, Authentication authentication,
                                RedirectAttributes redirectAttributes) {
        try {
            User upd = userService.updateInfo(user);
            model.addAttribute("successMessage", "Профиль успешно сохранён");
            model.addAttribute("user", upd);
            return "my-profile";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "edit-my-profile";
        }
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
