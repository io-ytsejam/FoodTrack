package com.backend.Controllers;

import com.backend.Dto.RecipeDto;
import com.backend.Models.RecipeEntity;
import com.backend.Repositories.RecipeEntityRepository;
import com.backend.Services.RecipeCommandService;
import com.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.AuthenticationException;

@RestController
public class RecipeEntityController{

    @Autowired
    private RecipeCommandService recipeCommandService;

    @Autowired
    private RecipeEntityRepository recipeRepository; //temporary

    @PostMapping("/api/addRecipe")
    public RedirectView postRecipe(@RequestBody RecipeDto recipeDto)
    {
        recipeCommandService.createRecipe(recipeDto);
        return new RedirectView("/api/currentUser/recipes");
    }

    @PutMapping("/api/updateRecipe/{id}")
    public RedirectView updateRecipe(@RequestBody RecipeDto recipeDto,@PathVariable long id)
    {
        RecipeEntity updatedRecipe = recipeCommandService.updateRecipe(id,recipeDto);
        if(updatedRecipe != null)
            return new RedirectView("/api/recipes/"+updatedRecipe.getRecipeid());
        else
            return new RedirectView("/index");
    }
    /*@PostMapping("/api/recipes/{id}")
    public RedirectView updateRecipe(@RequestBody RecipeDummy recipeDummy,@PathVariable Long id)
    {

    }*/

    @GetMapping("/api/recipes")
    public Page<RecipeEntity> getRecipes(Pageable pageable)
    {
        return recipeRepository.findAll(pageable);
    }

    @GetMapping("/api/currentUser/recipes")
    public ResponseEntity<Page<RecipeEntity>> getCurrentUserRecipes(Pageable pageable) throws AuthenticationException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //if(!(authentication instanceof AnonymousAuthenticationToken))
            return ResponseEntity.ok(recipeRepository.findByPersonNickname(authentication.getName(),pageable));
        //return (ResponseEntity<Page<RecipeEntity>>) ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT);

    }

    /*public RecipeEntityController(RecipeEntityRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }*/


}