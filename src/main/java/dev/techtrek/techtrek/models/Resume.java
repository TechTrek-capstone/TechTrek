package dev.techtrek.techtrek.models;

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

    // relationship to users table = many to one
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    //after placement reviews, add columns where V2, V3, etc. of resume are added dynamically
}
