package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User
{
    @Id @GeneratedValue
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

    public User(int userId, String nickname, String password, String firstname, String lastname) {
        this.userId=userId;
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
}