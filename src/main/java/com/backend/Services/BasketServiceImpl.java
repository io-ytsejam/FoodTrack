package com.backend.Services;

import com.backend.Dto.BasketAddDto;
import com.backend.Dto.BasketIngredientDto;
import com.backend.Models.Basket;
import com.backend.Models.BasketIngredient;
import com.backend.Models.IngredientEntity;
import com.backend.Repositories.BasketRepository;
import com.backend.Repositories.IngredientEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    BasketRepository basketRepository;

    @Autowired
    IngredientEntityRepository ingredientRepository;

    @Autowired
    UserService userService;

    @Autowired
    RecipeCommandService recipeService;

    @Override
    public Basket addBasket(BasketAddDto basketDto, String nickname)
            throws BadCredentialsException,ResourceNotFoundException {
        Basket basket = null;

        if (basketDto.getBasketid() != null) {
            basket = basketRepository.findByBasketid(basketDto.getBasketid()).orElse(null);
        }

        if (basket != null) {
            if (!basket.getBasketPersonNickname().equals(nickname))
                throw new BadCredentialsException("Cant't modify someone else's basket");
        } else {
            basket = new Basket();
            userService.findByNickname(nickname).addBasket(basket);
        }
        if (basketDto.getRecipeid() != null){
            try {
                recipeService.findByRecipeid(basketDto.getRecipeid()).addBasket(basket);
            }catch(ResourceNotFoundException e){
                throw e;
            }
        }
        basket=basketRepository.save(basket);
        if(basketDto.getIngredients()==null)
            return basket;
        basket.clearIngredients();
        for (BasketIngredientDto s : basketDto.getIngredients()) {
            IngredientEntity ingredient = ingredientRepository.findFirstByNameIgnoreCase(s.getName()).
                    orElseGet(() -> ingredientRepository.save(new IngredientEntity(s.getName())));
            BasketIngredient basketIngredient = new BasketIngredient();
            basketIngredient.setBasket(basket);
            basketIngredient.setIngredient(ingredient);
            basketIngredient.setCompleted(s.isCompleted());
            basketIngredient.updateId();
            basket.addIngredient(basketIngredient);
        }
        return basketRepository.save(basket);
    }

    @Override
    public Basket getBasketByBasketid(Long id, String nickname)
            throws BadCredentialsException, ResourceNotFoundException {
        Basket basket = basketRepository.findByBasketid(id).orElse(null);
        if (basket == null)
            throw new ResourceNotFoundException("No basket with given id");
        /*if(!basket.getPersonNickname().equals(nickname))
            throw new BadCredentialsException("Can't access someone else's basket");*/
        return basket;
    }

    @Override
    public Page<Basket> getBasketsByPersonNickname(String nickname, Pageable pageable)
            throws BadCredentialsException {
        return basketRepository.findByPersonNickname(nickname, pageable);
    }
}
