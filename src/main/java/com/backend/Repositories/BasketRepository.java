package com.backend.Repositories;


import com.backend.Models.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends PagingAndSortingRepository<Basket,Long> {
    Page<Basket> findByPersonNickname(String nickname, Pageable pageable);
    Optional<Basket> findByBasketid(Long basketid);
    boolean existsByBasketid(Long basketid);
}
