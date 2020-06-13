package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "INGREDIENT", schema = "TEST", catalog = "")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","recipes"})
public class IngredientEntity {
    @Id
    @Column(name = "INGREDIENTID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long ingredientid;

    @Basic
    @Column(name = "NAME",unique=true,nullable = false)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeEntity> recipes = new HashSet<RecipeEntity>();

    public void addRecipe(RecipeEntity recipeEntity) {
        this.recipes.add(recipeEntity);
        recipeEntity.getIngredients().add(this);
    }

    public void removeRecipe(RecipeEntity recipeEntity) {
        this.recipes.remove(recipeEntity);
        recipeEntity.getIngredients().remove(this);
    }

    public Set<RecipeEntity> getRecipes()
    {
        return recipes;
    }

    public IngredientEntity() {
    }

    public IngredientEntity(String name)
    {
        this.name=name;
    }

    public void setRecipes(Set<RecipeEntity> recipes) {
        this.recipes = recipes;
    }

    public long getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientEntity that = (IngredientEntity) o;

        if (ingredientid != that.ingredientid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ingredientid ^ (ingredientid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

