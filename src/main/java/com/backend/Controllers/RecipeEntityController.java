package com.backend.Controllers;

import com.backend.BackendApplication;
import com.backend.Dto.RecipeAddDto;
import com.backend.Dto.RecipeAddRemoveElementsDto;
import com.backend.Models.CommentEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Models.RecipeThumbnail;
import com.backend.Repositories.RecipeEntityRepository;
import com.backend.Services.RecipeCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/*
Get comments for given recipe id: @GetMapping(/api/recipes/{id}/comments?{page,size}) @PathVariable Long id

Post new recipe: @PostMapping(/api/recipes)
Body: String name, char ifexternal, String description, Collection{ optional Long ingredientid, String name },
(needs testing) Collection{ optional Long stepid, String stepdescription },(needs testing) Collection( optional Long photoid, String photoLink)
Response header: Location - link to new recipe

(unfinished) Replace existing recipe: @PutMapping(/api/recipes/{id})

(unfinished) Add elements to existing recipe: @PostMapping(/api/recipes/{id}

Get all recipes as page: @GetMapping(/api/recipes?{page,size})

Get recipe by id: @GetMapping(/api/recipes/{id}) @PathVariable Long id

Get recipes by current user as page: @GetMapping(/api/recipes/user?{page,size})

Delete recipe with given id: @DeleteMapping (/api/recipes/{id})

Add comment to recipe with given id: @PostMapping(/api/recipes/{id}/comment)
Body: String content

Rate recipe with given id: @PostMapping(/api/recipes/{id}/rate?{value}) @RequestParam Long value

*/
@RestController
public class RecipeEntityController{

    @Autowired
    private RecipeCommandService recipeCommandService;

    @Autowired
    private RecipeEntityRepository recipeRepository;

    private Logger logger=LoggerFactory.getLogger(BackendApplication.class);


    @GetMapping("/api/recipes/{id}/comments")
    public ResponseEntity<Page<CommentEntity>> getCommentsForRecipe(@PathVariable @NotBlank Long id,Pageable pageable)
            throws ResourceNotFoundException
    {
        HttpHeaders responseheaders=new HttpHeaders();
        try{
            responseheaders.add("Location","/api/recipes/"+id);
            return ResponseEntity.ok(recipeCommandService.getCommentsByRecipeid(id,pageable));
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }



    @PostMapping("/api/recipes")
    public ResponseEntity<RecipeEntity> postRecipe(@Valid @NonNull @RequestBody RecipeAddDto recipeDto)
            throws BadCredentialsException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders responseHeaders = new HttpHeaders();
        if (!(authentication instanceof AnonymousAuthenticationToken))
        {
            RecipeEntity recipe = recipeCommandService.createRecipe(recipeDto,authentication.getName());
            responseHeaders.add("Location","/api/recipes/"+recipe.getRecipeid());
            return new ResponseEntity(recipe,responseHeaders,HttpStatus.OK);
        }
        throw new BadCredentialsException("Anonymous user cannot post recipes");
    }

    @PostMapping("/api/recipes/{id}")
    public ResponseEntity<RecipeEntity> addToRecipe(@RequestBody RecipeAddRemoveElementsDto recipeDto, @PathVariable Long id)
     throws BadCredentialsException,ResourceNotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders responseheaders=new HttpHeaders();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            try{
                RecipeEntity recipe = recipeCommandService.addToRecipe(recipeDto,id,authentication.getName());
                responseheaders.add("Location","/api/recipes/"+recipe.getRecipeid());
            return new ResponseEntity(recipe,responseheaders,HttpStatus.OK);
            } catch (BadCredentialsException | ResourceNotFoundException e) {
                logger.error(e.getMessage());
                throw e;
            }
        }else
        {
            logger.error("Anonymous user can't modify recipe");
            throw new BadCredentialsException("Anonymous user can't modify recipe");
        }
    }

    @PutMapping("/api/recipes/{id}")
    public ResponseEntity<RecipeEntity> replaceRecipe(@RequestBody @Valid @NonNull RecipeAddDto recipeDto,
                                                @PathVariable Long id)
            throws BadCredentialsException,ResourceNotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders responseheaders=new HttpHeaders();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            try{
                RecipeEntity recipe = recipeCommandService.replaceRecipe(recipeDto,id,authentication.getName());
                responseheaders.add("Location","/api/recipes/"+recipe.getRecipeid());
                return new ResponseEntity(recipe,responseheaders,HttpStatus.OK);
            } catch (BadCredentialsException | ResourceNotFoundException e) {
                logger.error(e.getMessage());
                throw e;
            }
        }else
        {
            logger.error("Anonymous user can't modify recipe");
            throw new BadCredentialsException("Anonymous user can't modify recipe");
        }
    }

    @GetMapping("/api/recipes")
    public Page<RecipeEntity> getRecipes(Pageable pageable) throws BadCredentialsException
    {
        return recipeCommandService.getAllRecipes(pageable);
    }

    @GetMapping("/api/recipes/{id}")
    public ResponseEntity<RecipeEntity> getRecipeWithId(@PathVariable Long id) throws ResourceNotFoundException
    {
        RecipeEntity recipe;
        try {
            recipe = recipeCommandService.findByRecipeid(id);
        } catch (ResourceNotFoundException e) {
            logger.error("No recipe found with given id / @Get \"/api/recipes/"+id+"\"");
            throw e;
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/api/recipes/user")
    public ResponseEntity<Page<RecipeEntity>> getCurrentUserRecipes(Pageable pageable) throws BadCredentialsException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken)
            throw new BadCredentialsException("Cannot authenticate user");
        return ResponseEntity.ok(recipeCommandService.findByPersonNickname(authentication.getName(),pageable));
    }

    @DeleteMapping("/api/recipes/{id}")
    public ResponseEntity deleteRecipe(@PathVariable Long id) throws
            BadCredentialsException,ResourceNotFoundException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BadCredentialsException(("Anonymous user can't modify recipe"));
        }
        try{
        recipeCommandService.deleteByRecipeId(id,authentication.getName());
        } catch (BadCredentialsException | ResourceNotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        }
        return new ResponseEntity(HttpStatus.OK);
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

    @PostMapping("/api/recipes/{id}/comment")
    public ResponseEntity<RecipeEntity> addComment(@RequestBody @NotBlank String content, @PathVariable Long id)
            throws ResourceNotFoundException,BadCredentialsException
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders responseHeaders = new HttpHeaders();
        RecipeEntity recipe;
        if(authentication instanceof AnonymousAuthenticationToken)
            throw new BadCredentialsException("Anonymous user can't post comments");
        try {
             recipe = recipeCommandService.addComment(content, id, authentication.getName());
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        }
        responseHeaders.add("Location","/api/recipes/"+id);
        return new ResponseEntity(recipe,responseHeaders,HttpStatus.OK);
    }


    @PostMapping("/api/recipes/{id}/rate")
    public ResponseEntity<RecipeEntity> rateRecipe(@RequestParam Long value,@PathVariable Long id) throws
            ResourceNotFoundException,BadCredentialsException
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders responseHeaders = new HttpHeaders();
        if(!(authentication instanceof AnonymousAuthenticationToken))
        {
            RecipeEntity recipe;
            try{
            recipe= recipeCommandService.rateRecipe(value,id,authentication.getName());}
            catch (ResourceNotFoundException e) {
                logger.error(e.getMessage());
                throw e;
            }
            responseHeaders.add("Location","/api/recipes/"+id);
            return new ResponseEntity(recipe
                    ,responseHeaders,HttpStatus.CREATED);
        }
        throw new BadCredentialsException("Anonymous users can't rate recipes");
    }

    @PostMapping("/api/recipes/{id}/addPhoto")
    public ResponseEntity<RecipeEntity> addPhoto(@RequestBody String photo,@PathVariable Long id) throws
            BadCredentialsException,ResourceNotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders responseheaders=new HttpHeaders();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            try{
                RecipeEntity recipe = recipeCommandService.addPhoto(photo,id,authentication.getName());
                responseheaders.add("Location","/api/recipes/"+recipe.getRecipeid());
                return new ResponseEntity(recipe,responseheaders,HttpStatus.OK);
            } catch (BadCredentialsException | ResourceNotFoundException e) {
                logger.error(e.getMessage());
                throw e;
            }
        }else
        {
            logger.error("Anonymous user can't modify recipe");
            throw new BadCredentialsException("Anonymous user can't modify recipe");
        }
    }

    /*@GetMapping("/api/recipes/thumbnails")
    public Page<RecipeThumbnail> getRecipeThumbnails(Pageable pageable, Sort sort)
    {
        return recipeCommandService.getRecipeThumbnails(pageable,sort);
    }*/

    @GetMapping("/api/recipes/search")
    public Page<RecipeEntity> getRecipesLike(@RequestParam("like") String name,Pageable pageable)
    {
        return recipeCommandService.findByNameLike(name,pageable);
    }

    @GetMapping("/api/recipes/thumbnails")
    public Page<RecipeThumbnail> getRecipeThumbnailsLike(
            @Nullable @RequestParam("like") String name, Pageable pageable, Sort sort)
    {
        return recipeCommandService.getRecipeThumbnailsNameLike(
                name==null?"%":name,pageable,sort);
    }
}