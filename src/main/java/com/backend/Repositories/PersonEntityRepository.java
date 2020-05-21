package com.backend.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.Models.PersonEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people",path = "people")
public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {
    Page<PersonEntity> findByLastname(@Param("name")String lastName, Pageable pageable);
    PersonEntity findByPersonid(@Param("id")long id);
    Page<PersonEntity> findByFirstnameContainsIgnoreCase(@Param("name") String name,Pageable pageable);
    PersonEntity findByNickname(String username);
}