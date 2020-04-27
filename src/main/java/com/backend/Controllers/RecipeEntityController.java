package com.backend.Controllers;

import com.backend.Models.IngredientEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Repositories.RecipeEntityRepository;
import com.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Optional;

//SWITCH TO DTOS TO NOT ADD COMMENTS/RATINGS WHEN CREATING RECIPE

@RestController
public class RecipeEntityController{

    /*@Autowired
    private RecipeCommandService recipeCommandService; *///temporary?

    @Autowired
    private RecipeEntityRepository recipeRepository; //temporary?

    @Autowired
    private UserService userService;

    @PostMapping("/api/recipes")
    public ResponseEntity<String> postRecipe(@RequestBody RecipeEntity newRecipe)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
        {
            newRecipe.setPerson(userService.findByNickname(authentication.getName()));
            if(!newRecipe.getRatings().isEmpty()) //||newRecipe.getComments !=null //SWITCH TO DTO
                return new ResponseEntity<>("Can't add ratings",HttpStatus.BAD_REQUEST);
            recipeRepository.save(newRecipe);
            return new ResponseEntity("/api/recipes",HttpStatus.CREATED);
        }else
            return new ResponseEntity("/register",HttpStatus.FORBIDDEN);

    }

    //SWITCH TO DTO
    @PostMapping("/api/recipes/{id}")
    public ResponseEntity<RecipeEntity> addToRecipe(@RequestBody RecipeEntity newRecipe,@PathVariable Long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            if(authentication.getName().equals(recipeRepository.findById(id).get().getRecipePersonNickname())){
            return new ResponseEntity(recipeRepository.findById(id)
                    .map(recipe -> {
                        if(newRecipe.getName()!=null)
                            recipe.setName(newRecipe.getName());
                        if(newRecipe.getDescription()!=null)
                            recipe.setDescription(newRecipe.getDescription());
                        if(!newRecipe.getIngredients().isEmpty())
                            recipe.addAllPhotoEntities(newRecipe.getPhotoEntities());
                        if(!newRecipe.getIngredients().isEmpty())
                            recipe.addAllIngredients(newRecipe.getIngredients());
                        if(!newRecipe.getSteps().isEmpty())
                            recipe.addAllSteps(newRecipe.getSteps());
                        return  recipeRepository.save(recipe);
                    }),HttpStatus.ACCEPTED);}
            else
            {
                newRecipe.setRecipeid(id);
                newRecipe.setPerson(userService.findByNickname(authentication.getName()));
                return new ResponseEntity<>(recipeRepository.save(newRecipe),HttpStatus.ACCEPTED);
            }
        }else
            return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    //done //add comments //switch to dto
    @PutMapping("/api/recipes/{id}")
    public ResponseEntity<String> replaceRecipe(@RequestBody RecipeEntity newRecipe,@PathVariable Long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (recipeRepository.findById(id).isPresent() &&
                    recipeRepository.findById(id).get().getRecipePersonNickname().equals(authentication.getName())) {
                recipeRepository.findById(id)
                        .map(recipe -> {
                            recipe.setName(newRecipe.getName());
                            recipe.setDescription(newRecipe.getDescription());
                            recipe.setIfexternal(newRecipe.getIfexternal());
                            recipe.setIngredients(newRecipe.getIngredients());
                            recipe.setDescription(newRecipe.getDescription());
                            recipe.setSteps(newRecipe.getSteps());
                            //recipe.setPhotoEntities(newRecipe.getPhotoEntities());
                            recipe.removeAllPhotoEntities();
                            recipe.addAllPhotoEntities(newRecipe.getPhotoEntities());
                            return recipeRepository.save(recipe);
                        });
                return new ResponseEntity<>("/api/recipes/" + id
                        , HttpStatus.CREATED);
            } else
                return new ResponseEntity<>("/index",HttpStatus.FORBIDDEN);

        } else
            return new ResponseEntity<>("/register",HttpStatus.FORBIDDEN);
    }

    //tbd
    @GetMapping("/api/recipes")
    public Page<RecipeEntity> getRecipes(Pageable pageable)
    {
        return recipeRepository.findAll(pageable);
    }

    //done
    @GetMapping("/api/recipes/{id}")
    public ResponseEntity<Optional<RecipeEntity>> getRecipeWithId(@PathVariable Long id)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (recipeRepository.findById(id).isPresent()){
                return new ResponseEntity<>(recipeRepository.findById(id),HttpStatus.ACCEPTED);
            }
            else
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    }

    @GetMapping("/api/recipes/user")
    public ResponseEntity<Page<RecipeEntity>> getCurrentUserRecipes(Pageable pageable) throws AuthenticationException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //security config automatically denies access?

        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            return ResponseEntity.ok(recipeRepository.findByPersonNickname(
                    authentication.getName(), pageable));
        }else
        return (ResponseEntity<Page<RecipeEntity>>) ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT);
    }

    //done
    @DeleteMapping("/api/recipes/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (recipeRepository.findById(id).isPresent()) {
                if (recipeRepository.findById(id).get().getRecipePersonNickname().equals(authentication.getName())) {
                    recipeRepository.deleteById(id);
                    return new ResponseEntity<>("/api/recipes/user",HttpStatus.ACCEPTED);
                }else
                    return new ResponseEntity<>("Unauthorized delete attempt",HttpStatus.FORBIDDEN);
            } else
                return new ResponseEntity<>("No recipe with given id", HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>("/register", HttpStatus.FORBIDDEN);
    }


    //Unfinished: will ignore incomplete information in json i.e. wont remove ingredient if only ingredientid is given
    //due to how equals method works and oldRecipe not pulling info from database
    @PatchMapping("/api/recipes/{id}")
    public ResponseEntity<Optional<RecipeEntity>> removeFromRecipe(@RequestBody RecipeEntity oldRecipe,@PathVariable Long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            if(authentication.getName().equals(recipeRepository.findById(id).get().getRecipePersonNickname())){
                return new ResponseEntity(recipeRepository.findById(id)
                        .map(recipe -> {
                            //RecipeEntity tmpRecipe = new RecipeEntity();
                            //tmpRecipe.setName("tmp");
                            //tmpRecipe.set
                            if(!oldRecipe.getIngredients().isEmpty())
                                recipe.removeSomeIngredients(oldRecipe.getIngredients());
                            if(!oldRecipe.getPhotoEntities().isEmpty())
                                recipe.removeSomePhotoEntities(oldRecipe.getPhotoEntities());
                            if(!oldRecipe.getSteps().isEmpty())
                                recipe.removeSomeSteps(oldRecipe.getSteps());
                            return  recipeRepository.save(recipe);
                        }),HttpStatus.ACCEPTED);}
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else
            return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}