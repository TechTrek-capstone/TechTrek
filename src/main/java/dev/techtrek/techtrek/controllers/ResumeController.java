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

        List<Resume> resumes = resumeRepo.findAllByUser_Id(user.getId());
        model.addAttribute("resumes", resumes);
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

    // placement form submission for resume revision - not an endpoint
    @PostMapping("resume/revision")
    public String uploadResumeRevision(@RequestParam(name = "resumeRevisionUpload") String resumeURL,
                                       @RequestParam(name = "resumeId") long id) {
        Resume resume = resumeRepo.findById(id);
        resume.setRevision(resumeURL);
        resumeRepo.save(resume);
        return "redirect:/resume";
    }

    // placement form submission for resume notes - not an endpoint
    @PostMapping("resume/notes")
    public String uploadResumeNotes(@RequestParam(name = "resumeNotesUpload") String resumeNotes,
                                    @RequestParam(name = "resumeNotesId") long id) {
        Resume resume = resumeRepo.findById(id);
        resume.setPlacementNotes(resumeNotes);
        resumeRepo.save(resume);
        return "redirect:/resume";
    }
}