package dev.techtrek.techtrek.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "job_listings")
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "location", nullable = false)
    @NotBlank(message = "Job listing must have a location.")
    private String location;

    @Column(name = "is_remote", nullable = false)
    private Boolean isRemote;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "Job listing must have a title.")
    @Size(min = 5, message = "A title must be at least 5 characters.")
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Job listing must have a description.")
    @Size(min = 20, message = "A description must be at least 20 characters.")
    private String description;

    @Column(name = "required_skills", length = 3000)
    private String requiredSkills;

    @Column(name = "preferred_skills", length = 3000)
    private String preferredSkills;

    @Column(name = "apply_url", nullable = false, length = 2000, unique = true)
    @NotBlank(message = "Job listing must have an application link.")
    @Size(min = 20, message = "An application link must be at least 10 characters.")
    private String applyUrl;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "listing_date", nullable = false)
    private Date listingDate = new Date();

    @Column(name = "is_archived", nullable = false)
    private boolean isArchived;

    @ManyToOne
    @JoinColumn (name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
}
