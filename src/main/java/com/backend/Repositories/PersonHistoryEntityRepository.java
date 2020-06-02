package com.backend.Repositories;

import com.backend.Models.PersonHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface PersonHistoryEntityRepository extends JpaRepository<PersonHistoryEntity, Long> {
}
