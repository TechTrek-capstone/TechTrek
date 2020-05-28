package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.JobListing;
import dev.techtrek.techtrek.repositories.JobsRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JobListingController {

    // Dependency injection

    private JobsRepo jobsRepo;

    public JobListingController(JobsRepo jobsRepo){
        this.jobsRepo = jobsRepo;
    }


    // (All users) Index page with view of all jobs as cards

    @GetMapping("/jobs")
    public String showAllJobListings(Model model){
        List<JobListing> jobList = jobsRepo.findAll();
        model.addAttribute("jobs", jobList);
        return "jobs/index";
    }


    // (All users) Individual job listing view (by id)

    @GetMapping("/jobs/{id}")
    public String showJobListingById(@PathVariable long id, Model model){
        model.addAttribute("job", jobsRepo.getJobListingById(id));
        return "jobs/show";
    }


    // (Placement roles only) Post a job listing

    // View the job listing creation form
    @GetMapping(path = "/jobs/create")
    public String viewCreateJobListingForm(Model model) {
        model.addAttribute("job", new JobListing());
        return "jobs/create";
    }

    // Create the job listing
    @PostMapping(path = "/jobs/create")
    public String createJobListing(@ModelAttribute JobListing jobListing) {

        // FIXME: Adjust this validation to JobListing from Post from SpringBlog
//        // Check if errors present given title and body requirements defined in Post.java
//        if (validation.hasErrors()) {
//            model.addAttribute("errors", validation);
//            model.addAttribute("post", post);
//
//            // If errors present, reload the page with the errors listed as alerts above the form.
//            return "posts/create";
//        }

//        // If no errors, assign the user to the post...
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        post.setUser(user);

//        // Send an email confirmation using the emailService...
//        String emailSubject = "New Post Created";
//        String emailBody = "Successfully posted new blog post titled \"" + post.getTitle() + "\"!";
//        emailService.prepareAndSend(post, emailSubject, emailBody);


        // Save the job listing and redirect to jobs index
        jobsRepo.save(jobListing);
        return "redirect:/jobs";
    }


    // (Placement roles only) Edit a job listing

    // View the post info to be edited
    @GetMapping("/posts/{id}/edit")
    public String viewEditJobListingForm(@PathVariable long id, Model model) {
        model.addAttribute("job", jobsRepo.getOne(id));
        return "jobs/edit";
    }
}
