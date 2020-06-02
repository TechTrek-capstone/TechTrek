package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.Cohort;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.models.UserRole;
import dev.techtrek.techtrek.repositories.CohortsRepo;
import dev.techtrek.techtrek.repositories.UserRolesRepo;
import dev.techtrek.techtrek.repositories.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    // Dependency injection
    private Users users;
    private PasswordEncoder passwordEncoder;
    private CohortsRepo cohortsRepo;
    private UserRolesRepo userRolesRepo;

    public UserController(Users Users, CohortsRepo cohortsRepo, UserRolesRepo userRolesRepo, Users users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.cohortsRepo = cohortsRepo;
        this.userRolesRepo = userRolesRepo;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    // Landing page
    @GetMapping("/")
    public String showLandingPage(Model model) {
        List<Cohort> cohorts = cohortsRepo.findAll();
        List<UserRole> userRoles = userRolesRepo.findAll();
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("user", new User());
        return "index";
    }

    // Dashboard view
    @GetMapping("/home")
    public String showDashboard(Model model) {
        model.addAttribute("user", new User());
        return "users/index";
    }

    // Profile view
    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("user", new User());
        return "users/profile";
    }
}
