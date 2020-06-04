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


//
//    create table if not exists roles
//        (
//        id bigint auto_increment primary key,
//        name varchar(255) not null,
//        unique (name)
//        );
