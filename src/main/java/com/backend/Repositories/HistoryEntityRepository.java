package com.backend.Repositories;

import com.backend.Models.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HistoryEntityRepository extends JpaRepository<HistoryEntity, Long> {

}