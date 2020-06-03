package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.*;
import dev.techtrek.techtrek.repositories.CompaniesRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {

    // Dependency injection
    private CompaniesRepo companiesRepo;

    public CompanyController(CompaniesRepo companiesRepo) {
        this.companiesRepo = companiesRepo;
    }

// (Placement roles only) View all companies

    @GetMapping("/companies")
    public String viewAllCompanies(Model model){
        List<Company> companyList = companiesRepo.findAll();
        model.addAttribute("companies", companyList);
        return "companies/index";
    }

// (Placement roles only) Add a company

    // View the company creation form
    @GetMapping(path = "/companies/create")
    public String viewCreateCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "companies/create";
    }

    // Create the company
    @PostMapping(path = "/companies/create")
    public String createJobListing(@ModelAttribute Company company) {

        // Save the company to DB and redirect to companies index page (table of all companies)
        companiesRepo.save(company);
        return "redirect:/companies";
    }


// (Placement roles only) Edit a company

    // View the company info to be edited
    @GetMapping("/companies/{id}/edit")
    public String companyEditForm(@PathVariable long id, Model model) {
        model.addAttribute("company", companiesRepo.getOne(id));
        return "companies/edit";
    }

    // Submit the edited post information and update the DB
    @PostMapping("/companies/{id}/edit")
    public String postEdit(@PathVariable long id, @RequestParam(name = "name") String name) {

        // Get the company from DB
        Company company = companiesRepo.getCompanyById(id);

        // Set the name to the value in the name
        company.setName(name);


        // TODO: Set the image to the value in the image field
//        company.setImg(img); // Add img to RequestParam

        // Update the company in the DB
        companiesRepo.save(company);

        // Redirect to the companies index
        return "redirect:/companies";
    }


    // (Placement only) Delete a company
    @PostMapping("/companies/{id}/delete")
    public String postDelete(@PathVariable long id) {
        // Grab the post by the ID
        companiesRepo.deleteById(id);

        // Redirect to post index
        return "redirect:/companies";
    }
}