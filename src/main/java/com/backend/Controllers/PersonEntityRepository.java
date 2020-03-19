package com.backend.Controllers;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import com.backend.Models.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "people",path = "people")
public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {

    @Query (value = "SELECT * FROM PERSON p WHERE p.LASTNAME LIKE '?1'", nativeQuery =true)
    Page<PersonEntity> findAllByLastname(String lastName, Pageable pageable);

    @Query(value = "SELECT * FROM PERSON p WHERE p.PERSONID=?1",nativeQuery = true)
    PersonEntity findByPersonid(long id);

    @Query(value = "SELECT * FROM PERSON p WHERE p.FIRSTNAME LIKE '?1'", nativeQuery = true)
    Page<PersonEntity> findAllByFirstname(String name,Pageable pageable);

    @Query(value = "SELECT * FROM PERSON p WHERE p.NICKNAME LIKE '?1'", nativeQuery = true)
    Page<PersonEntity> findByNickname(String nickname,Pageable pageable);
}