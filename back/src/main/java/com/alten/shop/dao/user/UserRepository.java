package com.alten.shop.dao.user;

import com.alten.shop.utils.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
