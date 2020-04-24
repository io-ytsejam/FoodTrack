package com.backend.Services;

import com.backend.Dto.RecipeDto;
import com.backend.Models.RecipeEntity;

public interface RecipeCommandService { //add fetching
    public long createRecipe(RecipeDto recipeDto);
    public RecipeEntity updateRecipe(long id,RecipeDto recipeDto);
}
