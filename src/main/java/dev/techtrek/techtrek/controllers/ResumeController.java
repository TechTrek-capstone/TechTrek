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

    // FIXME: Need a DB table called resumes and Java Bean for Resume with foreign key to the User id.

    // FIXME: Need a ResumeRepo class to access data from the resumes table

    private ResumeRepo resumeRepo;
    private CohortsRepo cohortsRepo;

    public ResumeController(ResumeRepo resumeRepo, CohortsRepo cohortsRepo) {
        this.resumeRepo = resumeRepo;
        this.cohortsRepo = cohortsRepo;
    }

    @GetMapping("/resume")
    public String showResume(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        model.addAttribute("resumes", resumeRepo.findAllById(id));
        model.addAttribute("user", new User());
        List<Cohort> cohorts = cohortsRepo.findAll();
        model.addAttribute("cohorts", cohorts);
        return "resume/index";
    }


    @PostMapping("/resume/{id}")
    public String updateResumeDB(Model model,
                                 @PathVariable long id,
                                 @RequestParam(name = "resumeURL") String resumeURL) {


        // identify resume to add url to
        Resume resume = resumeRepo.getById(id);

        // update column
        resume.setLink(resumeURL);

        // save resume
        resumeRepo.save(resume);
        return "redirect:/resume";
    }

    @PostMapping("/resume/{id}/delete")
    public String deleteResume(@PathVariable long id) {
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
