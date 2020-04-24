package com.backend.Services;

import com.backend.Dto.RecipeDto;
import com.backend.Models.IngredientEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Repositories.IngredientEntityRepository;
import com.backend.Repositories.RecipeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public RecipeEntity updateRecipe(RecipeEntity newRecipe,Long id)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(recipeRepository.findById(id).isPresent() &&
                recipeRepository.findById(id).get().getRecipePersonNickname() == authentication.getName())
        {
            return recipeRepository.findById(id)
                    .map(recipe -> {
                        recipe.setName(newRecipe.getName());
                        recipe.setDescription(newRecipe.getDescription());
                        recipe.setIfexternal(newRecipe.getIfexternal());
                        recipe.setIngredients(newRecipe.getIngredients());

                        return recipeRepository.save(recipe);
                    })
                    .orElseGet(() -> {
                        newRecipe.setRecipeid(id);
                        return recipeRepository.save(newRecipe);
                    });
            }

        else
            return null;
    }

    @Override
    public Page<RecipeEntity> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Override
    public Page<RecipeEntity> findByPersonNickname(String nickname, Pageable pageable) {
        return recipeRepository.findByPersonNickname(nickname,pageable);
    }
}
