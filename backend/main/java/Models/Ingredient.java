package main.java.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient
{
    @Id
    @GeneratedValue
    @Column(name="INGREDIENT_ID", nullable=false)
    private int ingredientId;

    @Column(name="NAME", nullable=false)
    private String name;

    public Ingredient() {
    }

    public Ingredient(int ingredientId, String name) {
        this.ingredientId = ingredientId;
        this.name = name;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Recipe_ingredient> recipe_ingredientList = new ArrayList<>();
}