package com.backend.Controllers;

import com.backend.Models.RecipeEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RecipeRepo extends GenericRepo <RecipeEntity, Long> {
}
