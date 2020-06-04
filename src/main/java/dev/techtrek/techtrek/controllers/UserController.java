package dev.techtrek.techtrek.controllers;



import dev.techtrek.techtrek.models.*;
import dev.techtrek.techtrek.repositories.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;


@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private CohortsRepo cohortsRepo;
//    private UserWithRoles userWithRoles;
    private Roles roles;
    private JobsRepo jobsRepo;
    private EventsRepo eventsRepo;

    public UserController(Users users, PasswordEncoder passwordEncoder, CohortsRepo cohortsRepo, Roles roles, JobsRepo jobsRepo, EventsRepo eventsRepo) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.cohortsRepo = cohortsRepo;
        this.roles = roles;
        this.jobsRepo = jobsRepo;
        this.eventsRepo = eventsRepo;
    }



    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    // Landing page
    @GetMapping("/")
    public String showLandingPage(Model model) {
        List<Cohort> cohorts = cohortsRepo.findAll();
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("user", new User());
        return "index";
    }

    // Dashboard view
    @GetMapping("/home")
    public String showDashboard(Model model) {
        model.addAttribute("user", new User());
        List<JobListing> jobList = jobsRepo.findAll();
        model.addAttribute("jobs", jobList);
        List<EventListing> eventList = eventsRepo.findAll();
        model.addAttribute("eventsList", eventList);
        return "users/index";
    }

    // Profile view
    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("user", new User());
        return "users/profile";
    }
}
