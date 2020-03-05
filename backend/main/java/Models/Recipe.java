package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Recipe
{
    @Id @GeneratedValue
    @Column(name="RECIPE_ID", nullable=false)
    private int recipeId;

    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name="DESCRIPTION", nullable=false)
    private String description;

    @Column(name="IF_EXTERNAL", nullable=false)
    private boolean ifExternal;

    public Recipe(int recipeId, String name, String description, boolean ifExternal) {
        this.recipeId = recipeId;
        this.name = name;
        this.description = description;
        this.ifExternal = ifExternal;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
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

    public boolean isIfExternal() {
        return ifExternal;
    }

    public void setIfExternal(boolean ifExternal) {
        this.ifExternal = ifExternal;
    }
}