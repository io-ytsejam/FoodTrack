package com.backend.Models;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RecipeIngredientEntityPK implements Serializable {
    private long recipeRecipeid;
    private long ingredientIngredientid;

    @Column(name = "RECIPE_RECIPEID")
    @Id
    public long getRecipeRecipeid() {
        return recipeRecipeid;
    }

    public void setRecipeRecipeid(long recipeRecipeid) {
        this.recipeRecipeid = recipeRecipeid;
    }

    @Column(name = "INGREDIENT_INGREDIENTID")
    @Id
    public long getIngredientIngredientid() {
        return ingredientIngredientid;
    }

    public void setIngredientIngredientid(long ingredientIngredientid) {
        this.ingredientIngredientid = ingredientIngredientid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeIngredientEntityPK that = (RecipeIngredientEntityPK) o;

        if (recipeRecipeid != that.recipeRecipeid) return false;
        if (ingredientIngredientid != that.ingredientIngredientid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (recipeRecipeid ^ (recipeRecipeid >>> 32));
        result = 31 * result + (int) (ingredientIngredientid ^ (ingredientIngredientid >>> 32));
        return result;
    }
}
