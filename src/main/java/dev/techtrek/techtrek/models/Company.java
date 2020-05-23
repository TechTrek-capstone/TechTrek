package dev.techtrek.techtrek.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<JobListing> jobListingList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<PointOfContact> pointOfContactList;
}