package com.backend.Repositories;

import com.backend.Models.IngredientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientEntityRepository extends JpaRepository<IngredientEntity,Long> {
    Page<IngredientEntity> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
    boolean existsIngredientEntityByIngredientid(long id);
    boolean existsIngredientEntityByName(String name);
    boolean existsIngredientEntityByNameIgnoreCase(String name);
    boolean existsIngredientEntityByNameOrIngredientid(String name,long id);
    Optional<IngredientEntity> findFirstByName(String name);
    Optional<IngredientEntity> findFirstByNameIgnoreCase(String name);
}
