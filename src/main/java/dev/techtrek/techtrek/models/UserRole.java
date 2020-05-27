package dev.techtrek.techtrek.models;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userRole")
    private List<User> userList;

//    @Override
//    public String getUsername() {
//        return null;
//    }

//    @Override
//    public long getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }
//
//    public UserRole(User copy, long id, String name, List<User> userList) {
//        super(copy);
//        this.id = id;
//        this.name = name;
//        this.userList = userList;
//    }
//
//    public UserRole(long id, String name, List<User> userList) {
//        this.id = id;
//        this.name = name;
//        this.userList = userList;
//    }

    public UserRole() { }
}