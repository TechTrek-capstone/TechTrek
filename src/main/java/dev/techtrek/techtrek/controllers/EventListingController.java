package dev.techtrek.techtrek.controllers;

import dev.techtrek.techtrek.models.EventListing;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.repositories.EventsRepo;
import dev.techtrek.techtrek.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class EventListingController {

    // Dependency injection

    private EventsRepo eventsRepo;
    private Users usersRepo;

    public EventListingController(EventsRepo eventsRepo, Users usersRepo){
        this.eventsRepo = eventsRepo;
        this.usersRepo = usersRepo;
    }


    // (All users) Index page with view of all events as cards

    @GetMapping("/events")
    public String showAllEventListings(Model model){
        List<EventListing> eventList = eventsRepo.findAll();
        model.addAttribute("eventsList", eventList);
        return "events/index";
    }


    // (All users) Individual event listing view (by id)

    @GetMapping("/events/{id}")
    public String showEventListingById(@PathVariable long id, Model model){
        model.addAttribute("event", eventsRepo.getEventListingById(id));
        return "events/show";
    }


    // (Placement roles only) Post an event

    // View the event listing creation form
    @GetMapping(path = "/events/create")
    public String viewCreateEventListingForm(Model model) {
        model.addAttribute("event", new EventListing());
        return "events/create";
    }

    // Create the event
    @PostMapping(path = "/events/create")
    public String createEventListing(
            @ModelAttribute
            @Valid EventListing eventListing,
            Errors validation,
            Model model
    ) {
        // Check if errors present given requirements defined in EventListing.java
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("event", eventListing);

            // If errors present, reload the page with the errors listed as alerts above the form.
            return "events/create";
        }


        // FIXME: Make sure this has proper authentication
        // Add the user (placement) as the event creator
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        eventListing.setUser(user);

        // Save the job listing and redirect to events index
        eventsRepo.save(eventListing);
        return "redirect:/events";
    }


    // (Placement roles only) Edit a job listing

    // View the event info to be edited
    @GetMapping("/events/{id}/edit")
    public String viewEditEventListingForm(@PathVariable long id, Model model) {
        model.addAttribute("event", eventsRepo.getOne(id));
        return "events/edit";
    }

    // Submit the edited event information and update the DB
    @PostMapping("/events/{id}/edit")
    public String eventListingEdit(
            @PathVariable long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "date") Date date,
            @RequestParam(name = "time") LocalDateTime time,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "rsvp_url") String rsvpUrl,
            @RequestParam(name = "is_archived") Boolean isArchived
    ) {

        // Get the event listing from DB
        EventListing eventListing = eventsRepo.getEventListingById(id);

        // Set the title to the value in the title field
        eventListing.setTitle(title);

        // Set the description to the value in the description field
        eventListing.setDescription(description);

        // Set the location to the value in the location field
        eventListing.setLocation(location);

        // Set event date to value in date field
        eventListing.setDate(date);

        // Set event time to value in time field
        eventListing.setTime(time);

        // Set the RSVP url to URL in input field
        eventListing.setRsvpUrl(rsvpUrl);

        // FIXME: Why does isArchived not work???? :(

        // Update the event listing in the DB
        eventsRepo.save(eventListing);

        // Redirect to the events index
        return "redirect:/events";
    }


    // (Placement roles only) Delete an event
    @PostMapping("/events/{id}/delete")
    public String deleteEventListing(@PathVariable long id) {
        eventsRepo.deleteById(id);
        return "redirect:/events";
    }
}
