package main.java.Models;

import javax.persistence.*;

@Entity
@Table(name = "RECIPE_INGREDIENT", schema = "TEST", catalog = "")
@IdClass(RecipeIngredientEntityPK.class)
public class RecipeIngredientEntity {
    private long recipeRecipeid;
    private long ingredientIngredientid;

    public RecipeIngredientEntity() {
    }

    @Id
    @Column(name = "RECIPE_RECIPEID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getRecipeRecipeid() {
        return recipeRecipeid;
    }

    public void setRecipeRecipeid(long recipeRecipeid) {
        this.recipeRecipeid = recipeRecipeid;
    }

    @Id
    @Column(name = "INGREDIENT_INGREDIENTID")
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

        RecipeIngredientEntity that = (RecipeIngredientEntity) o;

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
