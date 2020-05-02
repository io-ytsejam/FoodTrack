package com.backend.Services;

import com.backend.Dto.RecipeAddDto;
import com.backend.Dto.RecipeAddRemoveElementsDto;
import com.backend.Models.CommentEntity;
import com.backend.Models.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

public interface RecipeCommandService { //not used
    public RecipeEntity createRecipe(RecipeAddDto recipeDto,String username);
    public RecipeEntity replaceRecipe(RecipeAddDto recipeDto,Long id,String username) throws
            BadCredentialsException,ResourceNotFoundException;
    public Page<RecipeEntity> findByPersonNickname (String nickname,Pageable pageable);
    public Page<RecipeEntity> getAllRecipes(Pageable pageable);
    public RecipeEntity saveRecipe(RecipeEntity recipeEntity);
    public RecipeEntity addToRecipe(RecipeAddRemoveElementsDto recipeDto,Long id, String username) throws
            BadCredentialsException, ResourceNotFoundException;
    public RecipeEntity addComment(String content,Long recipeId,String username) throws
            ResourceNotFoundException;
    public Page<CommentEntity> getCommentsByRecipeid(Long recipeId,Pageable pageable) throws
            ResourceNotFoundException;
    public RecipeEntity rateRecipe(long value,Long recipeId,String username) throws
            ResourceNotFoundException;
    public RecipeEntity findByRecipeid(Long recipeId) throws ResourceNotFoundException;
    public void deleteByRecipeId(Long recipeId,String username) throws
            BadCredentialsException,ResourceNotFoundException;
}
