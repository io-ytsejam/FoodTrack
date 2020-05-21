package com.backend.Exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class RecipeNotFoundException extends ResourceNotFoundException {

    RecipeNotFoundException(){
        super("No recipe with given id found");
    }
}
