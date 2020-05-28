package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.JobListing;
import dev.techtrek.techtrek.repositories.JobsRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class JobListController {
    private JobsRepo jobsRepo;

    public JobListController(JobsRepo jobsRepo){
        this.jobsRepo = jobsRepo;
    }

    @GetMapping("/jobs")
    public String showJobListings(Model model){
        List<JobListing> jobList = jobsRepo.findAll();
        model.addAttribute("jobs", jobList);
        return "jobs/index";
    }


}
