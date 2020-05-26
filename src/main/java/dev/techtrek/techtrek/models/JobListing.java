package dev.techtrek.techtrek.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
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
    private String location;

    @Column(name = "is_remote", nullable = false)
    private Boolean isRemote;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "required_skills", length = 3000)
    private String requiredSkills;

    @Column(name = "preferred_skills", length = 3000)
    private String preferredSkills;

    @Column(name = "apply_url", nullable = false, length = 2000, unique = true)
    private String applyUrl;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "listing_date", nullable = false)
    private Date listingDate;

    @Column(name = "is_archived", nullable = false)
    private boolean isArchived;

    @ManyToOne
    @JoinColumn (name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;



    // FIXME: does not create the many-to-many relationship or joins table :(

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name="job_listings_job_categories_join",
//            joinColumns={@JoinColumn(name="job_listing_id")},
//            inverseJoinColumns={@JoinColumn(name="job_category_id")}
//    )
//    private List<JobCategory> jobCategoriesList;
}
