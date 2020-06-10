package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.*;
import dev.techtrek.techtrek.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private ResumeRepo resumeRepo;


    public UserController(Users users, PasswordEncoder passwordEncoder, CohortsRepo cohortsRepo, JobsRepo jobsRepo, EventsRepo eventsRepo, SkillsRepo skillsRepo, Roles roles, ResumeRepo resumeRepo) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.cohortsRepo = cohortsRepo;
        this.jobsRepo = jobsRepo;
        this.eventsRepo = eventsRepo;
        this.skillsRepo = skillsRepo;
        this.roles = roles;
        this.resumeRepo = resumeRepo;
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
        List<Resume> resumes = resumeRepo.findAll();
        model.addAttribute("resumes", resumes);

        return "users/index";

    }

//    @GetMapping("/users/{id}/edit")
//    public String viewEditUserListingForm(@PathVariable long id, Model model){
//        // get user that is doing the editing
//        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = users.getOne(userWithRoles.getId());
//        model.addAttribute("user", user);
//
//        // pull record that needs to be edited
//    }

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

        List<Resume> resumes = resumeRepo.findAllByUser_Id(user.getId());
        model.addAttribute("resumes", resumes);

        return "users/profile";
    }

    @PostMapping("/profile")
    public String editProfile(

            @RequestParam(name = "userfirstname") String userFirstName,
            @RequestParam(name = "last_name") String lastName,

            @RequestParam(name = "bio_summary", required = false) String bioSummary,
            @RequestParam(name = "phone_number", required = false) String phoneNumber,

            @RequestParam(name = "email") String email,
            @RequestParam(name = "work_location", required = false) String workLocation,

            @RequestParam(name = "user_website", required = false) String userWebsite,
            @RequestParam(name = "linkedin_username", required = false) String linkedinUsername,

            @RequestParam(name = "github_username", required = false) String githubUsername,
            @RequestParam(name = "cohort", required = false) Cohort cohort,
            @RequestParam(name = "employment_status", required = false) EmploymentStatus employmentStatus,
            @RequestParam(name = "profile_pic", required = false) String profilePic,
            @RequestParam(name = "user_perm") String userPerm,
            @RequestParam(name = "skills", required = false) List<Skill> skills
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

    @GetMapping("/users/{id}")
    public String showUserById(@PathVariable long id, Model model) {
        model.addAttribute("student", users.getUserById(id));
        List<Cohort> cohorts = cohortsRepo.findAll();
        model.addAttribute("cohorts", cohorts);

        return "partials/partials :: userModalContents";
    }

    @PostMapping("/users/{id}")
    public String editUserData(
           @PathVariable long id,
            @RequestParam(name = "cohort") Cohort cohort,
            @RequestParam(name = "user_perm") String userPerm,
            @RequestParam(name = "enabled") Boolean isEnabled
    ) {
        User student = users.getUserById(id);
        student.setCohort(cohort);
        student.setUserPerm(userPerm);
        student.setIsEnabled(isEnabled);

        users.save(student);
        return "redirect:/home";
    }

}
