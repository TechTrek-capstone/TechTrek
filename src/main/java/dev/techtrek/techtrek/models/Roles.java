package dev.techtrek.techtrek.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")

public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;


}



