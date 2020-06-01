package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String showLandingPage(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/home")
    public String showDashboard(Model model){
        model.addAttribute("user", new User());
        return "users/index";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        model.addAttribute("user", new User());
        return "users/profile";
    }
}