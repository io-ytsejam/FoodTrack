package com.backend.Repositories;

import com.backend.Models.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;

@Repository
public interface CommentEntityRepository extends JpaRepository<CommentEntity,Long> {
    Page<CommentEntity> findAllByRecipeRecipeid(Long id, Pageable pageable);
}
