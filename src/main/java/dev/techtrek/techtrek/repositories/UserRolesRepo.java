package dev.techtrek.techtrek.repositories;
import dev.techtrek.techtrek.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepo extends JpaRepository<UserRole, Long> {
    UserRole getUserRoleById(Long id);
}