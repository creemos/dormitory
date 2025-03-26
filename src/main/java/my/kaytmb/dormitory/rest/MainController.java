package my.kaytmb.dormitory.rest;


import org.springframework.stereotype.Controller;
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
    public String home() {
        return "home"; // Отображение страницы входа
    }



}
