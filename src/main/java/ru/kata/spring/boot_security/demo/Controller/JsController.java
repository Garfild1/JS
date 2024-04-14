package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JsController {

    @GetMapping ("/admin")
    public String allUsers (Model model) {
        return "adminPanel";
    }

    @GetMapping("/user")
    public String showOneUser() {
        return "userPanel";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
