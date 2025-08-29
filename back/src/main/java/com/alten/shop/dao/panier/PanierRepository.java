package com.alten.shop.dao.panier;

import com.alten.shop.utils.entities.panier.Panier;
import com.alten.shop.utils.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanierRepository extends JpaRepository<Panier, Long> {
    Optional<Panier> findByUser(UserEntity user);
}

