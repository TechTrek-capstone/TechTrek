package dev.techtrek.techtrek.repositories;


import dev.techtrek.techtrek.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User, Long> {
        User findByUsername(String username);
    }


