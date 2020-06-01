package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.Cohort;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.models.UserRole;
import dev.techtrek.techtrek.repositories.CohortsRepo;
import dev.techtrek.techtrek.repositories.UserRolesRepo;
import dev.techtrek.techtrek.repositories.UsersRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    // Dependency injection
    private CohortsRepo cohortsRepo;
    private UserRolesRepo userRolesRepo;

    public UserController(UsersRepo usersRepo, CohortsRepo cohortsRepo, UserRolesRepo userRolesRepo) {
        this.cohortsRepo = cohortsRepo;
        this.userRolesRepo = userRolesRepo;
    }

    // Landing page
    @GetMapping("/")
    public String showLandingPage(Model model){
        List<Cohort> cohorts = cohortsRepo.findAll();
        List<UserRole> userRoles = userRolesRepo.findAll();
        model.addAttribute("userRoles", userRoles);
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