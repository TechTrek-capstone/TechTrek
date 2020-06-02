package dev.techtrek.techtrek.repositories;

import dev.techtrek.techtrek.models.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Roles extends CrudRepository<UserRole, Long> {
    @Query("select ur.role from UserRole ur, User u where u.email=?1 and ur.userId = u.id")
    List<String> ofUserWith(String username);
}
