package com.backend.Controllers;

import com.backend.Models.IngredientEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class IngredientRepo extends GenericRepo <IngredientEntity, Long> {
}
