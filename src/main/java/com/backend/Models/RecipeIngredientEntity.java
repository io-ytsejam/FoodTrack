package com.backend.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Recipe_Ingredient")
public class RecipeIngredientEntity {

    @Id
    @GeneratedValue
    private long recipeingredientId;

    public RecipeIngredientEntity() {
    }

    public long getRecipeingredientId() {
        return recipeingredientId;
    }

    public void setRecipeingredientId(long recipeingredientId) {
        this.recipeingredientId = recipeingredientId;
    }


   /* @OneToMany
    @JoinColumn(name="recipeid", referencedColumnName = "recipeingredientid")
    private List<RecipeEntity> recipes;

    @OneToMany
    @JoinColumn(name="ingredientid", referencedColumnName = "recipeingredientid")
    private List<IngredientEntity> ingredients;*/
}
