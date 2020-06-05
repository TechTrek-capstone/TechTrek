package dev.techtrek.techtrek.repositories;

import dev.techtrek.techtrek.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepo extends JpaRepository<Resume, Long> {
    Resume getById(long id);

    List<Resume> findAllByUser_Id(long id);
}
