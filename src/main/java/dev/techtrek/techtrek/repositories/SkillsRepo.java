package dev.techtrek.techtrek.repositories;

import dev.techtrek.techtrek.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepo extends JpaRepository<Skill, Long> {
    Skill getSkillById(Long id);
    Skill getSkillByName(String name);
}