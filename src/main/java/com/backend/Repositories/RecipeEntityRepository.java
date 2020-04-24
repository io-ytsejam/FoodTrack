package com.backend.Repositories;

import com.backend.Models.IngredientEntity;
import com.backend.Models.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/*
@RepositoryRestResource(collectionResourceRel = "recipes",path = "recipes")
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity, Long > {
    Page<RecipeEntity> findByName(String Name, Pageable pageable);
    RecipeEntity findByRecipeid(long id);
    Page<RecipeEntity> findAllByNameContains(String Name, Pageable pageable);
    //RecipeEntity save(@Param("id") Long id);
    //@Query("")
    //RecipeEntity save(RecipeEntity recipe);
}*/
@Repository
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity,Long>{
    Page<RecipeEntity> findByPersonNickname(String nickname,Pageable pageable);
}
