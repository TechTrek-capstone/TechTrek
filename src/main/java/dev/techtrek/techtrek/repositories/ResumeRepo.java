package dev.techtrek.techtrek.repositories;

import dev.techtrek.techtrek.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepo extends JpaRepository<Resume, Long> {
    Resume getById(long id);
}
