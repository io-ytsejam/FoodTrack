package com.backend.Controllers;

import com.backend.Models.IngredientEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Models.RecipeIngredientEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientEntityRepository extends JpaRepository<IngredientEntity, Long> {

    @Query(value = "SELECT * FROM INGREDIENT i WHERE i.NAME='?1'", nativeQuery = true)
    IngredientEntity findByName(String Name);

    @Query (value = "SELECT * FROM INGREDIENT i WHERE i.INGREDIENTID=?1",nativeQuery = true)
    IngredientEntity findByIngredientid(long id);
}

