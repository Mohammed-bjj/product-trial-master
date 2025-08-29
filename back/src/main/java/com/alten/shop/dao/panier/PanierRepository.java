package com.alten.shop.dao.panier;

import com.alten.shop.utils.entities.panier.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Long> {}

