package dev.techtrek.techtrek.models;



import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole extends User {

    public UserRole(User user) { super(user); }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public UserRole(long id, String user_student) {
    }

    public static UserRole student(User user) {
        return new UserRole(user.getId(), "USER_STUDENT");
    }
    public static UserRole placementTeam(User user) {
        return new UserRole(user.getId(), "USER_PLACE");
    }
    public static UserRole administrator(User user) {
        return new UserRole(user.getId(), "USER_ADMIN");
    }

    public UserRole() { }

    public UserRole(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
