package com.backend.Repositories;

import com.backend.Models.RecipeThumbnail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailRepository extends JpaRepository<RecipeThumbnail,Long> {
    Page<RecipeThumbnail> findAll(Pageable pageable);
    Page<RecipeThumbnail> findAllByNameLikeIgnoreCaseAndNicknameLike(String name,String nickname,Pageable pageable);
}
