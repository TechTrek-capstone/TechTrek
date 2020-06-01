package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.repositories.UsersRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
        private UsersRepo users;
        private PasswordEncoder passwordEncoder;

    public AuthenticationController(UsersRepo users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/profile";
    }
}