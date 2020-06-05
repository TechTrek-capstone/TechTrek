package dev.techtrek.techtrek.repositories;


import dev.techtrek.techtrek.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmailIgnoreCase(String email);

}


