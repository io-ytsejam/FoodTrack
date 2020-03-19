package com.backend.Controllers;

import com.backend.Models.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recipes",path = "recipes")
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity, Long > {

    @Query (value = "SELECT * FROM RECIPE r WHERE r.name = '?1'", nativeQuery =true)
    RecipeEntity findByName(String name);

    @Query (value = "SELECT * FROM RECIPE r WHERE r.RECIPEID = ?1", nativeQuery =true)
    RecipeEntity findByRecipeid(long id);

    @Query (value = "SELECT * FROM RECIPE r WHERE r.name LIKE '%?1%' ", nativeQuery = true)
    Page<RecipeEntity> findAllByNameContains(String name, Pageable pageable);

    @Query(value = "SELECT * FROM RECIPE r WHERE r.DESCRIPTION LIKE '%?1%' ", nativeQuery = true)
    Page<RecipeEntity> findAllByDescription(String description, Pageable pageable);

    @Query(value = "SELECT * FROM RECIPE r WHERE r.IFEXTERNAL LIKE '?1' ", nativeQuery = true)
    Page<RecipeEntity> findAllByIfexternal(char ifexternal, Pageable pageable);
}