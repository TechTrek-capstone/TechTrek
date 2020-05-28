package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.repositories.UsersRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private UsersRepo users;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepo users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
    return "redirect:/test";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/test";
    }

    @GetMapping("/test")
    public String test(@ModelAttribute User user) {
            return "test";
    }
}
