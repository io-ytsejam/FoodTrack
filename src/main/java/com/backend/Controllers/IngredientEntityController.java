package com.backend.Controllers;

import com.backend.Models.IngredientEntity;
import com.backend.Repositories.IngredientEntityRepository;
import com.backend.Repositories.RecipeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientEntityController {

    @Autowired
    private IngredientEntityRepository ingredientRepository;

    @GetMapping("/api/getAllIngredients")
    public Page<IngredientEntity> getIngredients(Pageable pageable)
    {
        return ingredientRepository.findAll(pageable);
    }

}
