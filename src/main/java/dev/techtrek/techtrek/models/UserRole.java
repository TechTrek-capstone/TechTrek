package dev.techtrek.techtrek.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


// FIXME: Does not make the user_roles table or relationship to users

@Data
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user_role")
    private List<User> userList;
}
