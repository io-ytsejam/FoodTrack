package com.backend.Models;

import javax.persistence.*;

@Entity
@Table(name = "PERSON", schema = "TEST", catalog = "")
public class PersonEntity {
    @Id
    @Column(name = "PERSONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long personid;
    private String nickname;
    private String password;
    private String firstname;
    private String lastname;

    //private @OneToMany List<RecipeEntity> recipes;

    public PersonEntity() {
    }

    public PersonEntity(String firstName,String lastname)
    {
        this.nickname="Default";
        this.firstname=firstName;
        this.lastname=lastname;
        this.password="Default";
    }


    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

    @Basic
    @Column(name = "NICKNAME")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (personid != that.personid) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (personid ^ (personid >>> 32));
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
    @Override
    public String toString(){
        return this.firstname+" "+this.lastname;
    }
}
