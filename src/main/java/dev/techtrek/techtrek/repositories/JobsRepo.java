package dev.techtrek.techtrek.repositories;
import dev.techtrek.techtrek.models.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepo extends JpaRepository<JobListing, Long> {
    JobListing getJobListingById(Long id);
}
