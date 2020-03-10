package main.java.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Person")
public class User
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID", nullable=false)
    private int userId;

    @Column(name="NICKNAME", nullable=false)
    private String nickname;

    @Column(name="PASSWORD", nullable=false)
    private String password;

    @Column(name="FIRSTNAME", nullable=false)
    private String firstname;

    @Column(name="LASTNAME", nullable=false)
    private String lastname;

    public User() {
    }

    public User(String nickname, String password, String firstname, String lastname) {
        this.nickname=nickname;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Recipe> recipeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private final List<User_history> user_histories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Rating> ratings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private final List<User_setting> user_settings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private final List<User_healthRestriction> user_healthRestrictions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private final List<User_preference> user_preferences = new ArrayList<>();
}