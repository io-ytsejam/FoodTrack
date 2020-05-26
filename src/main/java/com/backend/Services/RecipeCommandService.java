package com.backend.Services;

import com.backend.Dto.RecipeAddDto;
import com.backend.Dto.RecipeAddRemoveElementsDto;
import com.backend.Models.CommentEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Models.RecipeThumbnail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

public interface RecipeCommandService { //not used
    RecipeEntity createRecipe(RecipeAddDto recipeDto,String username);
    RecipeEntity replaceRecipe(RecipeAddDto recipeDto,Long id,String username) throws
            BadCredentialsException,ResourceNotFoundException;
    Page<RecipeEntity> findByPersonNickname (String nickname,Pageable pageable);
    Page<RecipeEntity> getAllRecipes(Pageable pageable);
    RecipeEntity saveRecipe(RecipeEntity recipeEntity);
    RecipeEntity addToRecipe(RecipeAddRemoveElementsDto recipeDto,Long id, String username) throws
            BadCredentialsException, ResourceNotFoundException;
    RecipeEntity addComment(String content,Long recipeId,String username) throws
            ResourceNotFoundException;
    Page<CommentEntity> getCommentsByRecipeid(Long recipeId,Pageable pageable) throws
            ResourceNotFoundException;
    RecipeEntity rateRecipe(long value,Long recipeId,String username) throws
            ResourceNotFoundException;
    RecipeEntity findByRecipeid(Long recipeId) throws ResourceNotFoundException;
    void deleteByRecipeId(Long recipeId,String username) throws
            BadCredentialsException,ResourceNotFoundException;
    Page<RecipeEntity> findByNameLike(String name,Pageable pageable);
    Page<RecipeThumbnail> getRecipeThumbnails(Pageable pageable, Sort sort);
    Page<RecipeThumbnail> getRecipeThumbnailsNameLike(String name,Pageable pageable,Sort sort);
}
