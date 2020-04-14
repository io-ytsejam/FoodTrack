package com.backend.Models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "RECIPE", schema = "TEST", catalog = "")
public class RecipeEntity {
    @Id
    @Column(name = "RECIPEID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long recipeid;

    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "IFEXTERNAL")
    private char ifexternal;

    //Do przetestowania (w bazie typ to RAW)
    /*@Basic
    @Column(name = "POSTED")
    private OffsetDateTime posted;

    @Basic
    @Column(name = "EDITED")
    private OffsetDateTime edited;*/

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private PersonEntity person;
//private @Version @JsonIgnore Long version;

    @ManyToMany
    @JoinTable(name = "recipe_ingredient",
            joinColumns = {@JoinColumn(name = "recipeid")},
            inverseJoinColumns = {@JoinColumn(name = "ingredientid")})
     private Set<IngredientEntity> ingredients = new HashSet<IngredientEntity>();

    @ManyToMany
    @JoinTable(name = "recipe_step",
            joinColumns = {@JoinColumn(name = "recipeid")},
            inverseJoinColumns = {@JoinColumn(name = "stepid")})
    private Set<StepEntity> steps = new HashSet<StepEntity>();

    @OneToMany(mappedBy = "recipeEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PhotoEntity> photoEntities = new ArrayList<PhotoEntity>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RatingEntity> ratings = new ArrayList<RatingEntity>();

    public void addRating(RatingEntity ratingEntity)
    {
        this.ratings.add(ratingEntity);
        ratingEntity.setRecipe(this);
    }

    public void addPhoto(PhotoEntity photoEntity)
    {
        this.photoEntities.add(photoEntity);
        photoEntity.setRecipeEntity(this);
    }

    public void addStep(StepEntity stepEntity) {
        this.steps.add(stepEntity);
        stepEntity.getRecipes().add(this);
    }

    public void removeStep(StepEntity stepEntity) {
        this.steps.remove(stepEntity);
        stepEntity.getRecipes().remove(this);
    }

     public void addIngredient(IngredientEntity p) {
        this.ingredients.add(p);
        p.getRecipes().add(this);
    }

    public void removeIngredient(IngredientEntity p) {
        this.ingredients.remove(p);
        p.getRecipes().remove(this);
    }

    public Set<IngredientEntity> getIngredients(){return ingredients;}

    public RecipeEntity() {
    }

    public RecipeEntity(String name, String description, char ifexternal
            , PersonEntity person){
        this.name=name;
        this.description=description;
        this.ifexternal=ifexternal;
        this.person=person;
    }

    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    public void setIngredients(Set<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<StepEntity> getSteps() {
        return steps;
    }

    public void setSteps(Set<StepEntity> steps) {
        this.steps = steps;
    }

    public List<PhotoEntity> getPhotoEntities() {
        return photoEntities;
    }

    public void setPhotoEntities(List<PhotoEntity> photoEntities) {
        this.photoEntities = photoEntities;
    }

    public List<RatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingEntity> ratings) {
        this.ratings = ratings;
    }

    public PersonEntity getPerson(){

        return person;
    }

    public void setPerson(PersonEntity person)
    {
        this.person=person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getIfexternal() {
        return ifexternal;
    }

    public void setIfexternal(char ifexternal) {
        this.ifexternal = ifexternal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeEntity that = (RecipeEntity) o;

        if (recipeid != that.recipeid) return false;
        if (ifexternal != that.ifexternal) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (person != null ? !person.equals(that.person) : that.person != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeid, name, description, ifexternal
                , person
        );
    }
}