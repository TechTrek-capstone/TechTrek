package dev.techtrek.techtrek.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "job_listings")
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(columnDefinition="BOOLEAN DEFAULT false", name = "is_remote", nullable = false)
    private Boolean isRemote;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "apply_url", nullable = false, length = 2000, unique = true)
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

//    @ManyToOne
//    @JoinColumn (name = "user_id")
//    private User user;
}
