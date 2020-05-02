package com.backend.Controllers;

import com.backend.Models.IngredientEntity;
import com.backend.Repositories.IngredientEntityRepository;
import com.backend.Repositories.RecipeEntityRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IngredientEntityController {

    @Autowired
    private IngredientEntityRepository ingredientRepository;

    @GetMapping("/api/ingredients")
    public Page<IngredientEntity> getIngredients(Pageable pageable)
    {
        return ingredientRepository.findAll(pageable);
    }

    @DeleteMapping("/api/ingredients/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable Long id)
    {
        ingredientRepository.deleteById(id);
        return new ResponseEntity("abcd", HttpStatus.ACCEPTED);
    }

    /*@GetMapping("/api/test")
    public ResponseEntity<String> test(@RequestParam String name)
    {
        if(ingredientRepository.existsIngredientEntityByName(name))
            return new ResponseEntity("true",HttpStatus.CREATED);
        return new ResponseEntity("false",HttpStatus.CREATED);
    }*/
}
