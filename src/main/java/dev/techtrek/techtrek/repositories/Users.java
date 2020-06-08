package dev.techtrek.techtrek.repositories;


import dev.techtrek.techtrek.models.Cohort;
import dev.techtrek.techtrek.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Users extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmailIgnoreCase(String email);
    List<User> findAllByCohort(Cohort cohort);

}


