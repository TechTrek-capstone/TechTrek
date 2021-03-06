package dev.techtrek.techtrek.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Table (name = "resumes")
@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "link")
    private String link;

    @Column (name = "type")
    private String type;

    @Column (name = "placement_notes", length = 5000)
    private String placementNotes;

    @Column (name = "revision")
    private String revision;

    @Column (name = "title", columnDefinition = "varchar(50) default 'Resume'", nullable = false)
    private String title = "Resume";

    @Column (name = "status")
    private String status;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "cohort_id")
    @JsonManagedReference
    private Cohort cohort;
}
