package dev.techtrek.techtrek.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @Column(name = "github_username")
    private String githubUsername;

    @Column(name = "linkedin_username")
    private String linkedinUsername;

    @Column(name = "cohort_name")
    private String cohortName;

    @Column(name = "employment_status")
    private EmploymentStatus employmentStatus;

    @Column(name = "graduation_date")
    private Date graduationDate;

    @Column(name = "bio_summary", length = 500)
    private String bioSummary;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @ManyToOne
    @JoinColumn(name = "cohort_id")
    private Cohort cohort;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<JobListing> jobListingList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<EventListing> eventListingList;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }
    public User(){}
}
