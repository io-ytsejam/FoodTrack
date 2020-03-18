package com.backend.Controllers;

import com.backend.Models.IngredientEntity;
import com.backend.Models.RecipeEntity;
import com.backend.Models.RecipeIngredientEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientEntityRepository extends JpaRepository<IngredientEntity, RecipeIngredientEntityPK> {
    //Page<IngredientEntity> findByName(String Name, Pageable pageable);
    //Iterable<RecipeEntity> findByRecipeid(long id);
    //IngredientEntity findByIngredientid(long id);
    //Iterable<IngredientEntity> findAllByName(Iterable<String>);
}