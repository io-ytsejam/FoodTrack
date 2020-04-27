package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "PERSON", schema = "FDTRCK", catalog = "")
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

    @OneToMany(mappedBy = "personEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PersonPreferenceEntity> personPreferenceEntities = new ArrayList<>();

    @OneToMany(mappedBy = "personEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HealthRestrictionPersonEntity> healthRestrictionPersonEntities = new ArrayList<>();

    @OneToMany(mappedBy = "personEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PersonSettingEntity> personSettingEntities = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RatingEntity> ratings = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "person_role",
            joinColumns = {@JoinColumn(name = "personid")},
            inverseJoinColumns = {@JoinColumn(name = "roleid")})
    private Collection<RoleEntity> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "person_recommendation",
            joinColumns = {@JoinColumn(name = "personid")},
            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
    private Set<RecommendationEntity> recommendationEntities = new HashSet<RecommendationEntity>();

    public PersonEntity(String firstName, String lastName, String email, String password, Collection<RoleEntity> roles) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.password = password;
        this.roles = roles;
    }

    public void addRecipe(RecipeEntity recipe)
    {
        this.recipes.add(recipe);
        recipe.setPerson(this);
    }

    public List<RecipeEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeEntity> recipes) {
        this.recipes = recipes;
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

    public List<PersonPreferenceEntity> getPersonPreferenceEntities() {
        return personPreferenceEntities;
    }

    public void setPersonPreferenceEntities(List<PersonPreferenceEntity> personPreferenceEntities) {
        this.personPreferenceEntities = personPreferenceEntities;
    }


    public void addPersonPreference(PersonPreferenceEntity personPreferenceEntity) {
        this.personPreferenceEntities.add(personPreferenceEntity);
        personPreferenceEntity.setPersonEntity(this);
    }

    public void addPersonSetting(PersonSettingEntity personSettingEntity) {
        this.personSettingEntities.add(personSettingEntity);
        personSettingEntity.setPersonEntity(this);
    }

    public void addPersonHealth(HealthRestrictionPersonEntity healthRestrictionPersonEntity)
    {
        this.healthRestrictionPersonEntities.add(healthRestrictionPersonEntity);
        healthRestrictionPersonEntity.setPersonEntity(this);
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

    public List<HealthRestrictionPersonEntity> getHealthRestrictionPersonEntities() {
        return healthRestrictionPersonEntities;
    }

    public void setHealthRestrictionPersonEntities(List<HealthRestrictionPersonEntity> healthRestrictionPersonEntities) {
        this.healthRestrictionPersonEntities = healthRestrictionPersonEntities;
    }

    public List<PersonSettingEntity> getPersonSettingEntities() {
        return personSettingEntities;
    }

    public void setPersonSettingEntities(List<PersonSettingEntity> personSettingEntities) {
        this.personSettingEntities = personSettingEntities;
    }

    public Set<RecommendationEntity> getRecommendationEntities() {
        return recommendationEntities;
    }

    public void setRecommendationEntities(Set<RecommendationEntity> recommendationEntities) {
        this.recommendationEntities = recommendationEntities;
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

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }
}
