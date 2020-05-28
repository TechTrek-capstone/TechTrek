package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.repositories.UsersRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private UsersRepo users;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepo users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showSignupForm(Model model){
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
