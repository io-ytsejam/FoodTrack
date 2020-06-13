package com.backend.Models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.validation.valueextraction.ExtractedValue;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BasketIngredientKey implements Serializable {

    @GeneratedValue
    @Column(name = "basketid")
    Long basketId;
    @GeneratedValue
    @Column(name = "ingredientid")
    Long IngredientId;

    public BasketIngredientKey() {
    }

    public Long getBasketId() {
        return basketId;
    }

    public Long getIngredientId() {
        return IngredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketIngredientKey that = (BasketIngredientKey) o;
        return basketId.equals(that.basketId) &&
                IngredientId.equals(that.IngredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, IngredientId);
    }
}
