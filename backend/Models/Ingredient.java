package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient
{
    @Id
    @GeneratedValue
    @Column(name="INGREDIENT_ID", nullable=false)
    private int ingredientId;

    @Column(name="NAME", nullable=false)
    private String name;

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
}