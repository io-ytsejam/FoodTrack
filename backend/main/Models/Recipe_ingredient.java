package main.java.Models;

import javax.persistence.*;

@Entity
public class Recipe_ingredient
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RECIPE_INGREDIENT_ID", nullable=false)
    private int recipeingredientId;

    @Column(name="QUANTITY", nullable=false)
    private String quantity;

    @Column(name="UNIT", nullable=false)
    private String unit;

    public Recipe_ingredient() {
    }

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;
}
