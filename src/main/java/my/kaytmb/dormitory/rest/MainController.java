package my.kaytmb.dormitory.rest;


import my.kaytmb.dormitory.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kay 26.03.2025
 */
@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Отображение страницы входа
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        return "home"; // Отображение страницы входа
    }



}
