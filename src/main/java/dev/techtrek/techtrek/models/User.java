package dev.techtrek.techtrek.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnore
    private String password;

    @Column(columnDefinition = "varchar(255) default ''", nullable = false, name = "userfirstname")
    private String userfirstname = "";

    @Column(columnDefinition = "varchar(255) default ''", nullable = false, name = "last_name")
    private String lastName = "";

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "github_username")
    private String githubUsername;

    @Column(name = "linkedin_username")
    private String linkedinUsername;

    @Column(name = "work_location")
    private String workLocation;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "employment_status")
    private EmploymentStatus employmentStatus = EmploymentStatus.AVAILABLE;

    @Column(columnDefinition = "varchar(1000) default ''", name = "bio_summary")
    private String bioSummary = "";

    @Column(name = "user_website")
    private String userWebsite;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(columnDefinition = "varchar(255) default 'student'", nullable = false, name = "user_perm")
    private String userPerm;

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
    @JsonManagedReference
    private Cohort cohort;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_skills",
        joinColumns={@JoinColumn(name="user_id")},
        inverseJoinColumns={@JoinColumn(name="skill_id")}
    )
    private List<Skill> skills;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<JobListing> jobListingList;

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
