package com.backend.Services;

import com.backend.Dto.RecipeAddDto;
import com.backend.Dto.RecipeAddRemoveElementsDto;
import com.backend.Models.*;
import com.backend.Repositories.IngredientEntityRepository;
import com.backend.Repositories.RecipeEntityRepository;
import com.backend.Repositories.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeCommandServiceImpl implements RecipeCommandService {

    @Autowired
    private final RecipeEntityRepository recipeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientEntityRepository ingredientRepository;

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    public RecipeCommandServiceImpl(RecipeEntityRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeEntity saveRecipe(RecipeEntity recipeEntity) {
        RecipeEntity recipeResponse = recipeRepository.save(recipeEntity);
        return recipeResponse;
    }

    @Override
    //@Transactional
    public RecipeEntity createRecipe(RecipeAddDto recipeDto,String username)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
        {
            RecipeEntity newRecipe=new RecipeEntity(recipeDto.getName(),
                    recipeDto.getDescription(),recipeDto.getIfexternal(),
                    userService.findByNickname(authentication.getName()));
            for(IngredientEntity i: recipeDto.getIngredients())
            {
                if(i.getName()!=null)
                    newRecipe.addIngredient(ingredientRepository.findFirstByNameIgnoreCase(i.getName()).
                            orElseGet(() -> {return ingredientRepository.save(i);}));
                else if(ingredientRepository.existsIngredientEntityByIngredientid(i.getIngredientid()))
                    newRecipe.addIngredient(ingredientRepository.findById(i.getIngredientid()).get());
            }
            //newRecipe.addAllIngredients(recipeDto.getIngredients());
            newRecipe.addAllSteps(recipeDto.getSteps());
            newRecipe.addAllPhotoEntities(recipeDto.getPhotos());

            //entityManager.close();
            return saveRecipe(newRecipe);

        }else {
            return null;
        }
    }

    //can I use setters for collections???
    @Override
    public RecipeEntity replaceRecipe(RecipeAddDto recipeDto, Long id, String username)
            throws BadCredentialsException, ResourceNotFoundException {
        RecipeEntity recipe= recipeRepository.findById(id).orElse(null);
        if(recipe == null)
            throw new ResourceNotFoundException("No recipe with given id");
        if(!recipe.getRecipePersonNickname().equals(username))
            throw new BadCredentialsException("Can't modify someone else's recipe");
        recipe.setName(recipeDto.getName());
            recipe.setIfexternal(recipeDto.getIfexternal());
        if(recipeDto.getDescription()!=null)
        recipe.setDescription(recipeDto.getDescription());
        Set<IngredientEntity> ingredients=new HashSet<IngredientEntity>(recipe.getIngredients());
        recipe.removeSomeIngredients(ingredients);
        for(IngredientEntity i: recipeDto.getIngredients())
        {
            if(i.getName()!=null)
                recipe.addIngredient(ingredientRepository.findFirstByNameIgnoreCase(i.getName()).
                        orElseGet(() -> {return ingredientRepository.save(i);}));
            else if(ingredientRepository.existsIngredientEntityByIngredientid(i.getIngredientid()))
                recipe.addIngredient(ingredientRepository.findById(i.getIngredientid()).get());
        }
        Set<StepEntity> steps=new HashSet<StepEntity>(recipe.getSteps());
        recipe.removeSomeSteps(steps);
        recipe.addAllSteps(recipeDto.getSteps());
        List<PhotoEntity> photosOld=recipe.getPhotoEntities();
        List<PhotoEntity> photosNew=recipeDto.getPhotos();
        List<Long> idList=photosNew.stream().map(photo -> photo.getPhotoid()).
                filter(pId -> pId!=null).collect(Collectors.toList());
        recipe.removeSomePhotoEntities(
                photosOld.stream().filter(photo-> !idList.contains(photo.getPhotoid())).collect(Collectors.toList()));
        recipe.addAllPhotoEntities(
                photosNew.stream().filter(photo -> !idList.contains(photo.getPhotoid())).collect(Collectors.toList()));
        return recipeRepository.save(recipe);
    }

    @Override
    public Page<RecipeEntity> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Override
    public Page<RecipeEntity> findByPersonNickname(String nickname, Pageable pageable) {
        return recipeRepository.findByPersonNickname(nickname,pageable);
    }

    //done
    @Override
    public RecipeEntity addToRecipe(RecipeAddRemoveElementsDto recipeDto,Long id,String username)
            throws BadCredentialsException,ResourceNotFoundException {
        RecipeEntity recipe= recipeRepository.findById(id).orElse(null);
        if(recipe == null)
            throw new ResourceNotFoundException("No recipe with given id");
        if(!recipe.getRecipePersonNickname().equals(username))
            throw new BadCredentialsException("Can't modify someone else's recipe");
        Set<IngredientEntity> ingredients=recipeDto.getIngredients();
         for(IngredientEntity i: recipeDto.getIngredients())
            {
                if(i.getName()!=null)
                    recipe.addIngredient(ingredientRepository.findFirstByNameIgnoreCase(i.getName()).
                            orElseGet(() -> {return ingredientRepository.save(i);}));
                else if(ingredientRepository.existsIngredientEntityByIngredientid(i.getIngredientid()))
                    recipe.addIngredient(ingredientRepository.findById(i.getIngredientid()).get());
            }
        //recipe.addAllIngredients(recipeDto.getIngredients());
        recipe.addAllSteps(recipeDto.getSteps());
        recipe.addAllPhotoEntities(recipeDto.getPhotos());
        return recipeRepository.save(recipe);

    }

    @Override
    public RecipeEntity addComment(String content, Long recipeId, String username) throws ResourceNotFoundException{

        RecipeEntity recipe = recipeRepository.findById(recipeId).map(recipeEntity -> {
            CommentEntity newComment=new CommentEntity();
            newComment.setRecipe(recipeEntity);
            newComment.setPerson(userService.findByNickname(username));
            newComment.setContent(content);
            recipeEntity.addComment(newComment);
            return recipeRepository.save(recipeEntity);
        }).orElse(null);
        if(recipe == null)
            throw new ResourceNotFoundException("No recipe with given id");
        return recipe;
    }

    @Override
    public Page<CommentEntity> getCommentsByRecipeid(Long recipeId, Pageable pageable) throws ResourceNotFoundException{
        if (recipeRepository.existsById(recipeId)) {
            List<CommentEntity> comments = recipeRepository.findById(recipeId).get().getComments();
            int start = (int) pageable.getOffset();
            int end = (int) ((start + pageable.getPageSize()) > comments.size() ? comments.size()
                    : (start + pageable.getPageSize()));
            Page<CommentEntity> page
                    = new PageImpl<CommentEntity>(comments.subList(start, end), pageable, comments.size());
            return page;
        }else
            throw new ResourceNotFoundException("No recipe with given id");
    }

    /*private long roundHalfDown(long value)
    {
        if(Math.floor(value)==value)
            return value;
        return (long) (((value-Math.floor(value))>0.5) ? Math.floor(value)+0.5 : Math.floor(value));
    }*/

    @Override
    public RecipeEntity rateRecipe(long value, Long recipeId, String username) throws ResourceNotFoundException{
        value= (long) Math.floor(value);
        if(value>10)
            value=10;
        else if(value<0)
            value=0;
        if(recipeRepository.existsById(recipeId)){
            RecipeEntity recipe=recipeRepository.findById(recipeId).get();
            List<RatingEntity> ratings = recipe.getRatings();
            for(RatingEntity r: ratings)
            {
                if(r.getPerson().getNickname().equals(username))
                {r.setValue(value);recipe.setRatings(ratings);return recipeRepository.save(recipe);}
            }
                RatingEntity newRating=new RatingEntity();
                newRating.setValue(value);
                newRating.setPerson(userService.findByNickname(username));
                newRating.setRecipe(recipe);
                recipe.addRating(newRating);

                return recipeRepository.save(recipe);
        }else
            throw new ResourceNotFoundException("No recipe with given id");
    }

    @Override
    public RecipeEntity findByRecipeid(Long recipeId) throws ResourceNotFoundException {
        RecipeEntity recipe = recipeRepository.findById(recipeId).orElse(null);
        if(recipe == null)
            throw new ResourceNotFoundException("No recipe with given id");
        return recipe;
    }

    @Override
    public void deleteByRecipeId(Long recipeId, String username) throws BadCredentialsException,ResourceNotFoundException {
        if(!recipeRepository.existsById(recipeId))
            throw new ResourceNotFoundException("No recipe with given id");
        if(!recipeRepository.findById(recipeId).get().getRecipePersonNickname().equals(username))
            throw new BadCredentialsException("Can't delete someone else's recipe");
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public Page<RecipeEntity> findByNameLike(String name, Pageable pageable) {
        return recipeRepository.findAllByNameLikeIgnoreCase(name,pageable);
    }

    @Override
    public Page<RecipeThumbnail> getRecipeThumbnails(Pageable pageable, Sort sort) { ;
        return thumbnailRepository.findAll(
                PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort));
    }

    @Override
    public Page<RecipeThumbnail> getRecipeThumbnailsNameLike(String name, Pageable pageable, Sort sort) {
        return thumbnailRepository.findAllByNameLikeIgnoreCase(name,
                PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort));
    }
}
