package dev.techtrek.techtrek.repositories;
import dev.techtrek.techtrek.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepo extends JpaRepository<Company, Long> {
    Company getCompanyById(Long id);
}