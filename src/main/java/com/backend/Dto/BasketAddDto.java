package com.backend.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class BasketAddDto {
    private Long basketid;
    //@NotBlank
    //private Long personid;
    private Long recipeid;
    @NotNull
    @NotEmpty
    private Set<String> ingredients;

    public Long getBasketid() {
        return basketid;
    }

    public void setBasketid(Long basketid) {
        this.basketid = basketid;
    }

    /*public Long getPersonid() {
        return personid;
    }*/

    /*public void setPersonid(Long personid) {
        this.personid = personid;
    }*/

    public Long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(Long recipeid) {
        this.recipeid = recipeid;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
