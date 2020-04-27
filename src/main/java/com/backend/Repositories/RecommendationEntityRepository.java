package com.backend.Repositories;

import com.backend.Models.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecommendationEntityRepository extends JpaRepository<RecommendationEntity, Long> {

}
