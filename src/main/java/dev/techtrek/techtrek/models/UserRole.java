package dev.techtrek.techtrek.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "user_roles")
public class UserRole  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


}
