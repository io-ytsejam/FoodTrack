package com.backend.Repositories;

import com.backend.Models.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity,Long>{
    Page<RecipeEntity> findByPersonNickname(String nickname,Pageable pageable);
    Page<RecipeEntity> findAllByNameLikeIgnoreCase(String name,Pageable pageable);
    Page<RecipeEntity> findByPersonNicknameLikeAndNameLikeIgnoreCase(String nickname
            ,String name,Pageable pageable);
}
