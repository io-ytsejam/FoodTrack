package com.backend.Repositories;

import com.backend.Models.SettingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SettingRepository extends CrudRepository<SettingEntity,Long> {
    Optional<SettingEntity> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
