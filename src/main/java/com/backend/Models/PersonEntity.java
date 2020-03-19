package com.backend.Models;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long personId;

    private String nickname;
    private String password;
    private String firstname;
    private String lastname;

    public PersonEntity()
    {

    }

    public PersonEntity(String nickname, String password, String firstname, String lastname)
    {
        this.nickname=nickname;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    /*@OneToMany
    @JoinColumn(name="recipeid", referencedColumnName = "personid")
    private List<RecipeEntity> recipes;*/

}
