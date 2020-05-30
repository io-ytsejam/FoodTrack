package com.backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BASKET_INGREDIENT", schema = "FDTRCK", catalog = "")
public class BasketIngredient {

    @EmbeddedId
    private BasketIngredientKey id=new BasketIngredientKey();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("basketid")
    @JoinColumn(name = "basketid",nullable = false,  updatable = false)
    private Basket basket;

    @JsonValue
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("ingredientid")
    @JoinColumn(name = "ingredientid",nullable = false,  updatable = false)
    private IngredientEntity ingredient;

    //future attributes

    public BasketIngredientKey getId() {
        return id;
    }

    public Basket getBasket() {
        return basket;
    }

    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public String getIngredientName(){
        return ingredient.getName();
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setIngredient(IngredientEntity ingredient) {
        this.ingredient = ingredient;
    }

    public void updateId(){
        id.basketId=basket.getBasketid();
        id.IngredientId=ingredient.getIngredientid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketIngredient that = (BasketIngredient) o;
        return Objects.equals(id, that.id) &&
                basket.equals(that.basket) &&
                ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basket);
    }
}