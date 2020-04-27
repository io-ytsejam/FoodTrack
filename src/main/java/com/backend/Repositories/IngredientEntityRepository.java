package com.backend.Repositories;

import com.backend.Models.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientEntityRepository extends JpaRepository<IngredientEntity,Long> {
}
