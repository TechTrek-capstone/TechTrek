package dev.techtrek.techtrek.models;

// Category ideas:
//    FRONT_END,
//    BACK_END,
//    FULL_STACK,
//    DEVOPS,
//    CYBERSECURITY,
//    WEB_DEVELOPMENT,
//    MOBILE_DEVELOPMENT,
//    EMBEDDED_SYSTEMS,
//    CLOUD_COMPUTING,
//    DATA_SCIENCE,
//    DATABASE_ADMIN;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="job_categories")
public class JobCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;


    // FIXME: does not create the many-to-many relationship

    @ManyToMany(mappedBy = "job_categories")
    private List<JobListing> jobListings;

}
