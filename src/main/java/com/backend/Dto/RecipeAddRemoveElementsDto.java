package com.backend.Dto;

import com.backend.Models.IngredientEntity;
import com.backend.Models.PhotoEntity;
import com.backend.Models.StepEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeAddRemoveElementsDto {
    private Set<IngredientEntity> ingredients = new HashSet<IngredientEntity>();
    private Set<StepEntity> steps = new HashSet<StepEntity>();
    private List<PhotoEntity> photos = new ArrayList<PhotoEntity>();

    public Set<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<StepEntity> getSteps() {
        return steps;
    }

    public void setSteps(Set<StepEntity> steps) {
        this.steps = steps;
    }

    public List<PhotoEntity> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoEntity> photos) {
        this.photos = photos;
    }
}
