package dev.techtrek.techtrek.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<User> users;

}
