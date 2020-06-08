package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.Cohort;
import dev.techtrek.techtrek.models.Resume;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.models.UserWithRoles;
import dev.techtrek.techtrek.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResumeController {

    private ResumeRepo resumeRepo;
    private CohortsRepo cohortsRepo;
    private Users users;

    public ResumeController(ResumeRepo resumeRepo, CohortsRepo cohortsRepo, Users users) {
        this.resumeRepo = resumeRepo;
        this.cohortsRepo = cohortsRepo;
        this.users = users;
    }

    @GetMapping("/resume")
    public String showResume(Model model) {
        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(userWithRoles.getId());
        model.addAttribute("user", user);

        model.addAttribute("resumesTBlock", resumeRepo.findAllByType("t-block"));
        model.addAttribute("resumesVertical", resumeRepo.findAllByType("vertical"));
//        model.addAttribute("user", new User());
        List<Cohort> cohorts = cohortsRepo.findAll();
        model.addAttribute("cohorts", cohorts);



        return "resume/index";
    }

    // Store Resume
    @PostMapping("/resume")
    public String createResume(@ModelAttribute Resume resume,
                               @RequestParam(name = "resumeURL") String resumeURL,
                               @RequestParam(name = "resumeType") String type,
                               @RequestParam(name = "resumeTitle") String resumeTitle) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        resume.setUser(user);
        resume.setType(type);
        resume.setLink(resumeURL);
        resume.setTitle(resumeTitle);
        resumeRepo.save(resume);
        return "redirect:/resume";
    }

    // Delete Resume
    @PostMapping("/resume/delete")
    public String deleteResume(@RequestParam(name = "deleteResumeId") long id) {
        resumeRepo.deleteById(id);
        return "redirect:/resume";
    }

    // Mapping for cohort dropdown - ajax request gets data here
    @GetMapping("/resume/{cohortId}")
    @ResponseBody
    public List<User> cohortStudents(@PathVariable long cohortId) {
        Cohort cohort = cohortsRepo.findCohortById(cohortId);
        return users.findAllByCohort(cohort);
    }

    // Mapping for student dropdown - ajax request gets data here
    @GetMapping("/resume/student/{studentId}")
    @ResponseBody
    public List<Resume> studentResumes(@PathVariable long studentId) {
        return resumeRepo.findAllByUser_Id(studentId);
    }

    @PostMapping("resume/revision")
    public String uploadTBlockRevision(@RequestParam(name = "resumeRevisionUpload") String resumeURL,
                                       @RequestParam(name = "resumeRevisionId") long id) {
        Resume resume = resumeRepo.findById(id);
        resume.setRevision(resumeURL);
        resumeRepo.save(resume);

        return "redirect:/resume";
    }
}