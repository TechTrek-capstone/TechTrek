package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.*;
import dev.techtrek.techtrek.repositories.CompaniesRepo;
import dev.techtrek.techtrek.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {
private Users users;
    // Dependency injection
    private CompaniesRepo companiesRepo;

    public CompanyController(CompaniesRepo companiesRepo, Users users) {
        this.companiesRepo = companiesRepo;
        this.users = users;
    }

// (Placement roles only) View all companies

    @GetMapping("/companies")
    public String viewAllCompanies(Model model){
        List<Company> companyList = companiesRepo.findAll();
        model.addAttribute("companies", companyList);
        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(userWithRoles.getId());
        model.addAttribute("user", user);
        return "companies/index";
    }

// (Placement roles only) Add a company

    // View the company creation form
    @GetMapping(path = "/companies/create")
    public String viewCreateCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(userWithRoles.getId());
        model.addAttribute("user", user);
        return "companies/create";
    }

    // Create the company
    @PostMapping(path = "/companies/create")
    public String createCompany(@ModelAttribute Company company) {

        // Save the company to DB and redirect to companies index page (table of all companies)
        companiesRepo.save(company);
        return "redirect:/companies";
    }


// (Placement roles only) Edit a company

    // View the company info to be edited
    @GetMapping("/companies/{id}/edit")
    public String companyEditForm(@PathVariable long id, Model model) {
        model.addAttribute("company", companiesRepo.getOne(id));
        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(userWithRoles.getId());
        model.addAttribute("user", user);
        return "companies/edit";
    }

    // Submit the edited post information and update the DB
    @PostMapping("/companies/{id}/edit")
    public String companyEdit(
            @PathVariable long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "logo_img") String logoImg
            ) {

        // Get the company from DB
        Company company = companiesRepo.getCompanyById(id);

        // Set the name to the value in the name
        company.setName(name);

        // Set the image to the value in the image field
        company.setLogoImg(logoImg);

        // Update the company in the DB
        companiesRepo.save(company);

        // Redirect to the companies index
        return "redirect:/companies";
    }


    // (Placement only) Delete a company
    @PostMapping("/companies/{id}/delete")
    public String companyDelete(@PathVariable long id) {
        // Grab the post by the ID
        companiesRepo.deleteById(id);

        // Redirect to post index
        return "redirect:/companies";
    }
}
