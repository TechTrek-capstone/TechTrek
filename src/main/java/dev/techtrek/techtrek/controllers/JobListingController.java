package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.Company;
import dev.techtrek.techtrek.models.JobListing;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.repositories.CompaniesRepo;
import dev.techtrek.techtrek.repositories.JobsRepo;
import dev.techtrek.techtrek.repositories.UsersRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class JobListingController {

    // Dependency injection

    private JobsRepo jobsRepo;
    private UsersRepo usersRepo;
    private CompaniesRepo companiesRepo;

    public JobListingController(JobsRepo jobsRepo, UsersRepo usersRepo, CompaniesRepo companiesRepo){
        this.jobsRepo = jobsRepo;
        this.usersRepo = usersRepo;
        this.companiesRepo = companiesRepo;
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
        List<Company> companies = companiesRepo.findAll();
        model.addAttribute("companies", companies);
        return "jobs/create";
    }

    // Create the job listing
    @PostMapping(path = "/jobs/create")
    public String createJobListing(
            @ModelAttribute
            @Valid JobListing jobListing,
            Errors validation,
            Model model
    ) {
        // Check if errors present given requirements defined in JobListing.java
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("job", jobListing);

            // If errors present, reload the page with the errors listed as alerts above the form.
            return "jobs/create";
        }

        // FIXME: Make sure this has proper authentication
        // Add the user (placement) as the job listing creator
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jobListing.setUser(user);

        // Save the job listing and redirect to jobs index
        jobsRepo.save(jobListing);
        return "redirect:/jobs";
    }


    // (Placement roles only) Edit a job listing

    // View the job info to be edited
    @GetMapping("/jobs/{id}/edit")
    public String viewEditJobListingForm(@PathVariable long id, Model model) {
        model.addAttribute("job", jobsRepo.getOne(id));
        return "jobs/edit";
    }

    // Submit the edited job listing information and update the DB
    @PostMapping("/jobs/{id}/edit")
    public String jobListingEdit(
            @PathVariable long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "is_remote") Boolean isRemote,
            @RequestParam(name = "required_skills") String requiredSkills,
            @RequestParam(name = "preferred_skills") String preferredSkills,
            @RequestParam(name = "apply_url") String applyUrl,
            @RequestParam(name = "is_archived") Boolean isArchived,
            @RequestParam(name = "company") Company company) {

        // Get the job listing from DB
        JobListing jobListing = jobsRepo.getJobListingById(id);

        // Set the title to the value in the title field
        jobListing.setTitle(title);

        // Set the description to the value in the description field
        jobListing.setDescription(description);

        // Set the location to the value in the location field
        jobListing.setLocation(location);

        // Set if job is remote to the boolean value
        jobListing.setIsRemote(isRemote);

        // Set the required skills to the value in the required skills field
        jobListing.setRequiredSkills(requiredSkills);

        // Set the preferred skills to the value in the preferred skills field
        jobListing.setPreferredSkills(preferredSkills);

        // Set the application link to the value in the apply url field
        jobListing.setApplyUrl(applyUrl);

        // FIXME: Why does isArchived not work???? :(

        // Set the company to the value in the company field
        jobListing.setCompany(company);

        // Update the job listing in the DB
        jobsRepo.save(jobListing);

        // Redirect to the jobs index
        return "redirect:/jobs";
    }


    // (Placement roles only) Delete a job listing
    @PostMapping("/jobs/{id}/delete")
    public String deleteJobListing(@PathVariable long id) {
        jobsRepo.deleteById(id);
        return "redirect:/jobs";
    }
}