package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PERSON", schema = "TEST", catalog = "")
public class PersonEntity {
    @Id
    @Column(name = "PERSONID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long personid;

    @Basic
    @Column(name = "NICKNAME")
    private String nickname;

    @Basic
    @Column(name = "PASSWORD")
    @JsonIgnore
    private  String password;

    @Basic
    @Column(name = "FIRSTNAME")
    private String firstname;

    @Basic
    @Column(name = "LASTNAME")
    private String lastname;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RecipeEntity> recipes = new ArrayList<RecipeEntity>();

    @ManyToMany
    @JoinTable(name = "healthrestriction_person",
            joinColumns = {@JoinColumn(name = "personid")},
            inverseJoinColumns = {@JoinColumn(name = "healthrestrictionid")})
    private Set<HealthRestrictionEntity> healthRestrictionEntities = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "person_preference",
            joinColumns = {@JoinColumn(name = "personid")},
            inverseJoinColumns = {@JoinColumn(name = "preferenceid")})
    private Set<PreferenceEntity> preferences = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "person_setting",
            joinColumns = {@JoinColumn(name = "personid")},
            inverseJoinColumns = {@JoinColumn(name = "settingid")})
    private Set<SettingEntity> settings = new HashSet<SettingEntity>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RatingEntity> ratings = new ArrayList<>();

    public void addRecipe(RecipeEntity recipe)
    {
        this.recipes.add(recipe);
        recipe.setPerson(this);
    }

    public void addSetting(SettingEntity settingEntity)
    {
        this.settings.add(settingEntity);
        settingEntity.getPersonEntities().add(this);
    }

    public void removeSetting (SettingEntity settingEntity)
    {
        this.settings.remove(settingEntity);
        settingEntity.getPersonEntities().remove(this);
    }

    public void addPreference(PreferenceEntity preferenceEntity)
    {
        this.preferences.add(preferenceEntity);
        preferenceEntity.getPersonEntities().add(this);
    }

    public void removePreference (PreferenceEntity preferenceEntity)
    {
        this.preferences.remove(preferenceEntity);
        preferenceEntity.getPersonEntities().remove(this);
    }

    public void addRestriction(HealthRestrictionEntity healthRestrictionEntity)
    {
        this.healthRestrictionEntities.add(healthRestrictionEntity);
        healthRestrictionEntity.getPersonEntities().add(this);
    }

    public void removeRestriction (HealthRestrictionEntity healthRestrictionEntity)
    {
        this.healthRestrictionEntities.remove(healthRestrictionEntity);
        healthRestrictionEntity.getPersonEntities().remove(this);
    }

    public List<RecipeEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeEntity> recipes) {
        this.recipes = recipes;
    }

    public Set<HealthRestrictionEntity> getHealthRestrictionEntities() {
        return healthRestrictionEntities;
    }

    public void setHealthRestrictionEntities(Set<HealthRestrictionEntity> healthRestrictionEntities) {
        this.healthRestrictionEntities = healthRestrictionEntities;
    }

    public Set<SettingEntity> getSettings() {
        return settings;
    }

    public void setSettings(Set<SettingEntity> settings) {
        this.settings = settings;
    }

    public List<RatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingEntity> ratings) {
        this.ratings = ratings;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public void addComment(CommentEntity comment)
    {
        this.comments.add(comment);
        comment.setPerson(this);
    }

    public void addRating(RatingEntity ratingEntity)
    {
        this.ratings.add(ratingEntity);
        ratingEntity.setPerson(this);
    }

    public Set<PreferenceEntity> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<PreferenceEntity> preferences) {
        this.preferences = preferences;
    }

    /*public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private String[] roles;*/

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }
    /*public void setPassword(String password) {
        this.password=PASSWORD_ENCODER.encode(password);
    }*/

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
