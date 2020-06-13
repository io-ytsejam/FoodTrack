package com.backend.Services;

import com.backend.Dto.BasketAddDto;
import com.backend.Models.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.StringReader;

public interface BasketService {
    Basket addBasket(BasketAddDto basketDto,String nickname) throws BadCredentialsException,ResourceNotFoundException;
    Basket getBasketByBasketid(Long id, String nickname) throws BadCredentialsException, ResourceNotFoundException;
    Page<Basket> getBasketsByPersonNickname(String nickname, Pageable pageable) throws BadCredentialsException;
}
