package dev.techtrek.techtrek.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @Column(columnDefinition = "varchar(255) default ''", nullable = false, name = "userfirstname")
    private String userfirstname = "";

    @Column(columnDefinition = "varchar(255) default ''", nullable = false, name = "last_name")
    private String lastName = "";

    @Column(columnDefinition = "varchar(255) default ''", nullable = false, name = "phone_number")
    private String phoneNumber = "";

    @Column(columnDefinition = "varchar(255) default ''", nullable = false, name = "github_username")
    private String githubUsername = "";

    @Column(columnDefinition = "varchar(255) default ''", nullable = false, name = "linkedin_username")
    private String linkedinUsername = "";

    @Column(columnDefinition = "varchar(255)", name = "work_location")
    private String workLocation;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "employment_status")
    private EmploymentStatus employmentStatus = EmploymentStatus.AVAILABLE;

    @Column(columnDefinition = "text", nullable = false, name = "bio_summary")
    private String bioSummary = "";

    @Column(name = "role_id")
    private Long roleId;

    @Column(columnDefinition = "varchar(255) default ''", nullable = false, name = "user_website")
    private String userWebsite = "";

    @Column(name = "profile_pic")
    private String profilePic;

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
        return userfirstname + " " + lastName;
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
