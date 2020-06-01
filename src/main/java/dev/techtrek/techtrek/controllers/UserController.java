package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.Cohort;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.repositories.CohortsRepo;
import dev.techtrek.techtrek.repositories.UsersRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    // Dependency injection
    private UsersRepo usersRepo;
    private CohortsRepo cohortsRepo;

    public UserController(UsersRepo usersRepo, CohortsRepo cohortsRepo) {
        this.usersRepo = usersRepo;
        this.cohortsRepo = cohortsRepo;
    }

    // Landing page
    @GetMapping("/")
    public String showLandingPage(Model model){
        List<Cohort> cohorts = cohortsRepo.findAll();
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("user", new User());
        return "index";
    }

    // Dashboard view
    @GetMapping("/home")
    public String showDashboard(Model model){
        model.addAttribute("user", new User());
        return "users/index";
    }

    // Profile view
    @GetMapping("/profile")
    public String showProfile(Model model){
        model.addAttribute("user", new User());
        return "users/profile";
    }
}