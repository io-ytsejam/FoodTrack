package com.backend.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Ingredient")
public class IngredientEntity {

    @Id
    @GeneratedValue
    private long ingredientId;

    private String name;

    public IngredientEntity(String name)
    {
        this.name=name;
    }

    public IngredientEntity()
    {}

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IngredientEntity{" +
                "ingredientId=" + ingredientId +
                ", name='" + name + '\'' +
                '}';
    }

    //@ManyToOne
   // @JoinColumn(name="recipeingredientid")
    //private RecipeIngredientEntity recipeIngredientEntity;
}
