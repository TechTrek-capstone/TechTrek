package dev.techtrek.techtrek.repositories;
import dev.techtrek.techtrek.models.JobListing;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Date;

public interface JobsRepo extends JpaRepository<JobListing, Long> {
    JobListing getJobListingById(Long id);
}
