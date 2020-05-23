package dev.techtrek.techtrek.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cohorts")
public class Cohort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cohort")
    private List<User> studentList;
}
