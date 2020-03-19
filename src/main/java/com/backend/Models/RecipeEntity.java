package com.backend.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue
    private long recipeId;

    private String name;

    private String description;

    private char ifExternal;

    public RecipeEntity() {
    }

    public RecipeEntity(String name, String description, char ifExternal)
    {
        this.name=name;
        this.description=description;
        this.ifExternal=ifExternal;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
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

    public char getIfExternal() {
        return ifExternal;
    }

    public void setIfExternal(char ifExternal) {
        this.ifExternal = ifExternal;
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "recipeId=" + recipeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ifExternal=" + ifExternal +
                '}';
    }

    /*@ManyToOne
    @JoinColumn(name = "recipeingredientid")
    private RecipeIngredientEntity recipeIngredientEntity;

    @ManyToOne
    @JoinColumn(name = "personid")
    private PersonEntity personEntity;*/
}
