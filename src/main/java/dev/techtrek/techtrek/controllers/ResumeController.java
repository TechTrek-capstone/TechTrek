package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.Cohort;
import dev.techtrek.techtrek.models.Resume;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ResumeController {

    private ResumeRepo resumeRepo;
    private CohortsRepo cohortsRepo;
    private Users users;

    public ResumeController(ResumeRepo resumeRepo, CohortsRepo cohortsRepo, Users users) {
        this.resumeRepo = resumeRepo;
        this.cohortsRepo = cohortsRepo;
        this.users  = users;
    }

    @GetMapping("/resume")
    public String showResume(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        model.addAttribute("resumesTBlock", resumeRepo.findAllByType("t-block"));
        model.addAttribute("resumesVertical", resumeRepo.findAllByType("vertical"));
        model.addAttribute("user", new User());
        List<Cohort> cohorts = cohortsRepo.findAll();
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("user", user);



        return "resume/index";
    }

    // Store Resume
    @PostMapping("/resume")
    public String createResume(@ModelAttribute Resume resume,
                               @RequestParam(name = "resumeURL") String resumeURL,
                               @RequestParam(name = "resumeType") String type) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        resume.setUser(user);
        resume.setType(type);
        resume.setLink(resumeURL);
        resumeRepo.save(resume);
        return "redirect:/resume";
    }

    // Delete Resume
    @PostMapping("/resume/delete")
    public String deleteResume(@RequestParam(name = "deleteResumeId") long id) {
        resumeRepo.deleteById(id);
        return "redirect:/resume";
    }
}
// @RequestMapping("/check")
// @ResponseBody
// public String check(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
//     boolean a = getSomeResult();
//     if (a == true) {
//         model.addAttribute("alreadySaved", true);
//         return view;
//     } else {
//         model.addAttribute("alreadySaved", false);
//         return view;
//     }
// }
