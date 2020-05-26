package dev.techtrek.techtrek.services;

import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.models.UserRole;
import dev.techtrek.techtrek.repositories.UsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UsersRepo users;

    public UserDetailsLoader(UsersRepo users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserRole(user);
    }
}
