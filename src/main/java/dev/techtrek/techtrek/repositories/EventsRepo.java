package dev.techtrek.techtrek.repositories;
import dev.techtrek.techtrek.models.EventListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepo extends JpaRepository<EventListing, Long> {
    EventListing getEventListingById(Long id);
}