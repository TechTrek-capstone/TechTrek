package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.*;
import dev.techtrek.techtrek.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private CohortsRepo cohortsRepo;
    private JobsRepo jobsRepo;
    private EventsRepo eventsRepo;
    private SkillsRepo skillsRepo;
    private Roles roles;


    public UserController(Users users, PasswordEncoder passwordEncoder, CohortsRepo cohortsRepo, JobsRepo jobsRepo, EventsRepo eventsRepo, SkillsRepo skillsRepo, Roles roles) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.cohortsRepo = cohortsRepo;
        this.jobsRepo = jobsRepo;
        this.eventsRepo = eventsRepo;
        this.skillsRepo = skillsRepo;
        this.roles = roles;

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
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("user", new User());
        return "index";
    }

    // Dashboard view
    @GetMapping("/home")
    public String showDashboard(Model model) {
        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(userWithRoles.getId());
        model.addAttribute("user", user);
        List<JobListing> jobList = jobsRepo.findAll();
        model.addAttribute("jobs", jobList);
        List<EventListing> eventList = eventsRepo.findAll();
        model.addAttribute("eventsList", eventList);
        List<User> userList = users.findAll();
        model.addAttribute("userList", userList);
        return "users/index";

    }

    // Profile view
    @GetMapping("/profile")
    public String showProfile(Model model) {
        List<Cohort> cohorts = cohortsRepo.findAll();
        model.addAttribute("cohorts", cohorts);

        List<Skill> skills = skillsRepo.findAll();
        model.addAttribute("skills", skills);

        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(userWithRoles.getId());
        model.addAttribute("user", user);

        return "users/profile";
    }

    @PostMapping("/profile")
    public String editProfile(

            @RequestParam(name = "userfirstname") String userFirstName,
            @RequestParam(name = "last_name") String lastName,

            @RequestParam(name = "bio_summary") String bioSummary,
            @RequestParam(name = "phone_number") String phoneNumber,

            @RequestParam(name = "email") String email,
            @RequestParam(name = "work_location") String workLocation,

            @RequestParam(name = "user_website") String userWebsite,
            @RequestParam(name = "linkedin_username") String linkedinUsername,

            @RequestParam(name = "github_username") String githubUsername,
            @RequestParam(name = "cohort") Cohort cohort,
            @RequestParam(name = "employment_status") EmploymentStatus employmentStatus,
            @RequestParam(name = "profile_pic") String profilePic,
            @RequestParam(name = "user_perm") String userPerm,
            @RequestParam(name = "skills") List<Skill> skills
    ) {
        // Sets user based off the spring security authentication. This is based on role.
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        // Sets user as new so that we can properly save to the database. Otherwise the submitted data would only
        // persist for the current session.
        User currentUser = new User(user);
        currentUser.setUserfirstname(userFirstName);

        currentUser.setGithubUsername(githubUsername);
        currentUser.setBioSummary(bioSummary);
        currentUser.setUserWebsite(userWebsite);
        currentUser.setEmail(email);
        currentUser.setWorkLocation(workLocation);
        currentUser.setLastName(lastName);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setLinkedinUsername(linkedinUsername);
        currentUser.setCohort(cohort);
        currentUser.setEmploymentStatus(employmentStatus);
        currentUser.setProfilePic(profilePic);
        currentUser.setUserPerm(userPerm);
        currentUser.setSkills(skills);

        users.save(currentUser);

        // Just going to return the same page to view the updates.
        return "redirect:/profile";
    }

}
