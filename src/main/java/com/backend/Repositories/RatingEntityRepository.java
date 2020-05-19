package com.backend.Repositories;

import com.backend.Models.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//for testing
@Repository//RestResource(collectionResourceRel = "ratings",path = "ratings")
public interface RatingEntityRepository extends JpaRepository<RatingEntity,Long> {
    public void deleteAllByPersonPersonid(@Param("id") Long id);
}
