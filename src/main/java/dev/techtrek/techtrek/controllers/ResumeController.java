package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.Cohort;
import dev.techtrek.techtrek.models.Resume;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.models.UserWithRoles;
import dev.techtrek.techtrek.repositories.*;
import dev.techtrek.techtrek.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/resume")
    public String showResume(Model model) {
        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(userWithRoles.getId());
        model.addAttribute("user", user);

        Resume resume = new Resume();
        model.addAttribute("resume", resume);
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
        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = users.getOne(userWithRoles.getId());

        resume.setCohort(user.getCohort());
        resume.setStatus("Pending");
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

    // Ajax GET request from the cohorts dropdown. returns list of users to populate 2nd dropdown
    @GetMapping("/resume/{cohortId}")
    @ResponseBody
    public List<User> cohortStudents(@PathVariable long cohortId) {
        Cohort cohort = cohortsRepo.findCohortById(cohortId);
        return users.findAllByCohort(cohort);
    }

    // Ajax GET request for populating resumes table by cohort
    @GetMapping("/resume/cohort/{cohortId}")
    @ResponseBody
    public List<Resume> allStudentResumes(@PathVariable long cohortId) { return resumeRepo.findAllByCohort_Id(cohortId); }

    // Ajax GET request for populating resumes table by student
    @GetMapping("/resume/student/{studentId}")
    @ResponseBody
    public List<Resume> studentResumes(@PathVariable long studentId) { return resumeRepo.findAllByUser_Id(studentId); }

    // placement form submission for resume revision - not an endpoint
    @PostMapping("resume/revision")
    public String uploadResumeRevision(@RequestParam(name = "resumeRevisionUpload") String resumeURL,
                                       @RequestParam(name = "resumeId") long id) {
        Resume resume = resumeRepo.findById(id);
        resume.setRevision(resumeURL);
        resume.setStatus("Reviewed!");
        resumeRepo.save(resume);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        User student = resume.getUser();

        mailMessage.setTo(student.getEmail());
        mailMessage.setSubject("Your resume has been reviewed!");
        mailMessage.setFrom("no_reply@techtrek.dev");
        mailMessage.setText("Your resume has been reviewed. You can see it here: "  + "https://techtrek.dev/resume");

        emailSenderService.sendEmail(mailMessage);


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
