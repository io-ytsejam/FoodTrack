package com.backend.Models;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "BASKET", schema = "FDTRCK", catalog = "")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","person","recipe"})
public class Basket {

    @Id
    @Column(name = "BASKETID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long basketid;

    @ManyToOne(optional = false,fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "personid")
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recipeid")
    private RecipeEntity recipe;

    @OneToMany(mappedBy = "basket",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<BasketIngredient> ingredients = new HashSet<BasketIngredient>();

    public Basket() {
    }

    public long getBasketid() {
        return basketid;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public Set<BasketIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<BasketIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setBasketid(long basketid) {
        this.basketid = basketid;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

    public void addIngredient(BasketIngredient ingredient){this.ingredients.add(ingredient);}

    public void clearIngredients(){this.ingredients.clear();}

    @JsonGetter
    @JsonProperty("personid")
    public Long getPersonId(){
        return person.getPersonid();
    }

    @JsonGetter
    @JsonProperty("username")
    public String getBasketPersonNickname(){
        return person.getNickname();
    }

    @JsonGetter
    @JsonProperty("recipeid")
    public Long getRecipeId(){
        return recipe==null ? null :recipe.getRecipeid();
    }

    @JsonGetter
    @JsonProperty("recipe")
    public String getRecipeName(){
        return recipe==null? null : recipe.getName();
    }

    @JsonGetter
    @JsonProperty("ingredients")
    public List<String> getIngredientNames(){
        return ingredients.stream().map(BasketIngredient::getIngredientName).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return basketid == basket.basketid &&
                Objects.equals(person, basket.person) &&
                Objects.equals(recipe, basket.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketid);
    }
}
