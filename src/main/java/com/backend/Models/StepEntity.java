package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STEP", schema = "FDTRCK", catalog = "")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","recipes"})
public class StepEntity {
    @Id
    @Column(name = "STEPID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long stepid;

    @Basic
    @Column(name = "STEPDESCRIPTION")
    private String stepdescription;

    @ManyToMany(mappedBy = "steps")
    private Set<RecipeEntity> recipes = new HashSet<RecipeEntity>(); //need many to many?

    public void addRecipe(RecipeEntity recipeEntity) {
        this.recipes.add(recipeEntity);
        recipeEntity.getSteps().add(this);
    }

    public void removeRecipe(RecipeEntity recipeEntity) {
        this.recipes.remove(recipeEntity);
        recipeEntity.getSteps().remove(this);
    }

    public Set<RecipeEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeEntity> recipes) {
        this.recipes = recipes;
    }

    public StepEntity() {
    }

    public StepEntity(String stepdescription)
    {
        this.stepdescription=stepdescription;
    }

    public long getStepid() {
        return stepid;
    }

    public void setStepid(long stepid) {
        this.stepid = stepid;
    }

    public String getStepdescription() {
        return stepdescription;
    }

    public void setStepdescription(String stepdescription) {
        this.stepdescription = stepdescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepEntity that = (StepEntity) o;

        if (stepid != that.stepid) return false;
        if (stepdescription != null ? !stepdescription.equals(that.stepdescription) : that.stepdescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (stepid ^ (stepid >>> 32));
        result = 31 * result + (stepdescription != null ? stepdescription.hashCode() : 0);
        return result;
    }
}
