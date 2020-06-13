package com.backend.Controllers;

import com.backend.BackendApplication;
import com.backend.Dto.BasketAddDto;
import com.backend.Models.Basket;
import com.backend.Services.BasketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BasketController {

    @Autowired
    BasketService basketService;

    private Logger logger= LoggerFactory.getLogger(BackendApplication.class);

    @PostMapping("/api/baskets")
    public ResponseEntity addBasket(@Valid @RequestBody BasketAddDto basketDto)
    throws BadCredentialsException,ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders responseheaders=new HttpHeaders();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            try{
                Basket basket = basketService.addBasket(basketDto,authentication.getName());
                responseheaders.add("Location","/api/baskets/"+basket.getBasketid());
                return new ResponseEntity(basket,responseheaders,HttpStatus.OK);
            } catch (BadCredentialsException|ResourceNotFoundException e) {
                logger.error(e.getMessage());
                throw e;
            }
        }else
        {
            logger.error("Anonymous user can't add basket");
            throw new BadCredentialsException("Anonymous user can't add basket");
        }
    }

    @GetMapping("/api/baskets/user")
    public Page<Basket> getAllUserBaskets(Pageable pageable) throws BadCredentialsException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            return basketService.getBasketsByPersonNickname(authentication.getName(),pageable);
        }else
        {
            logger.error("Anonymous user can't add basket");
            throw new BadCredentialsException("Anonymous user can't add basket");
        }
    }

    @GetMapping("/api/baskets/{id}")
    public Basket getBasketByBasketId(@PathVariable Long id)
        throws BadCredentialsException,ResourceNotFoundException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            try{
                return basketService.getBasketByBasketid(id,authentication.getName());
            }catch(BadCredentialsException | ResourceNotFoundException e){
                logger.error(e.getMessage());
                throw e;
            }
        }else
        {
            logger.error("Anonymous user can't basket");
            throw new BadCredentialsException("Anonymous user can't basket");
        }
    }
}
