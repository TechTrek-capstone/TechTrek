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

    @Column(name = "enabled")
    private Boolean isEnabled;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "github_username")
    private String githubUsername;

    @Column(name = "linkedin_username")
    private String linkedinUsername;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "employment_status")
    private EmploymentStatus employmentStatus;

    @Column(name = "bio_summary", length = 500)
    private String bioSummary;

    @Column(name = "role_id")
    private Long roleId;

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

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<JobListing> jobListingList;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<EventListing> eventListingList;




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


    public void setEnabled(boolean b) {
        this.isEnabled = b;
    }
}
