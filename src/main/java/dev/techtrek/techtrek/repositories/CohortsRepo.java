package dev.techtrek.techtrek.repositories;
import dev.techtrek.techtrek.models.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortsRepo extends JpaRepository<Cohort, Long> {
    Cohort getCohortById(Long id);
}