package com.backend.Services;

import com.backend.Dto.RecipeDto;
import com.backend.Models.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeCommandService { //not used
    public long createRecipe(RecipeDto recipeDto);
    public RecipeEntity updateRecipe(RecipeEntity recipeEntity,Long id);
    public Page<RecipeEntity> findByPersonNickname (String nickname,Pageable pageable);
    public Page<RecipeEntity> getAllRecipes(Pageable pageable);
}
