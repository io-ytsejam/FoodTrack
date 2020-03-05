package main.java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Recipe_ingredient
{
    @Id @GeneratedValue
    @Column(name="RECIPE_INGREDIENT_ID", nullable=false)
    private int recipeingredientId;

    @Column(name="QUANTITY", nullable=false)
    private String quantity;

    @Column(name="UNIT", nullable=false)
    private String unit;

    public Recipe_ingredient(int recipeingredientId, String quantity, String unit) {
        this.quantity=quantity;
        this.recipeingredientId=recipeingredientId;
        this.unit=unit;
    }

    public int getRecipeingredientId() {
        return recipeingredientId;
    }

    public void setRecipeingredientId(int recipeingredientId) {
        this.recipeingredientId = recipeingredientId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
