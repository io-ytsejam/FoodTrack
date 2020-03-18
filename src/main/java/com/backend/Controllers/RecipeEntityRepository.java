package com.backend.Controllers;

import com.backend.Models.PersonPreferenceEntity;
import com.backend.Models.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(collectionResourceRel = "recipes",path = "recipes")
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity, Long > {
    Page<RecipeEntity> findByName(String Name, Pageable pageable);
    RecipeEntity findByRecipeid(long id);
    Page<RecipeEntity> findAllByNameContains(String Name, Pageable pageable);
}