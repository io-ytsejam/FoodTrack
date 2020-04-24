package com.backend.Services;

import com.backend.Dto.RecipeDto;
import com.backend.Models.IngredientEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Repositories.IngredientEntityRepository;
import com.backend.Repositories.RecipeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeCommandServiceImpl implements RecipeCommandService {

    @Autowired
    private final RecipeEntityRepository recipeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientEntityRepository ingredientRepository;
    public RecipeCommandServiceImpl(RecipeEntityRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public long createRecipe(RecipeDto recipeDto)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        RecipeEntity recipe = new RecipeEntity(
                recipeDto.getName()
                ,recipeDto.getDescription(),
                recipeDto.getIfexternal(),
                userService.findByNickname(authentication.getName()));
        List<IngredientEntity> ingredientList = ingredientRepository.findAll();
        if(ingredientList == recipeDto.getIngredients())
            recipe.setIngredients(recipeDto.getIngredients());
        recipe.setPhotoEntities(recipeDto.getPhotoEntities());
        recipe.setSteps(recipeDto.getSteps());
        return recipeRepository.save(recipe).getRecipeid();
    }

    @Override
    public RecipeEntity updateRecipe(long id,RecipeDto recipeDto)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(recipeRepository.findById(id).isPresent() &&
                recipeRepository.findById(id).get().getRecipePersonNickname() == authentication.getName())
        {
            RecipeEntity existingRecipe = recipeRepository.findById(id).get();
            existingRecipe.setName(recipeDto.getName());
            existingRecipe.setDescription(recipeDto.getDescription());
            existingRecipe.setIngredients(recipeDto.getIngredients());
            existingRecipe.setPhotoEntities(recipeDto.getPhotoEntities());
            existingRecipe.setSteps(recipeDto.getSteps());

            RecipeEntity updatedRecipe = recipeRepository.save(existingRecipe);
            return updatedRecipe;
            }

        else
            return null;
    }
}
