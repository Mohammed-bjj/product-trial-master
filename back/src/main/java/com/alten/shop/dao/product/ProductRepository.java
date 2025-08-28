package com.alten.shop.dao.product;

import com.alten.shop.utils.entities.product.Product;
import com.alten.shop.utils.entities.product.InventoryStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:category IS NULL OR :category = '' OR LOWER(p.category) = LOWER(:category)) AND " +
            "(:inventoryStatus IS NULL OR p.inventoryStatus = :inventoryStatus)")
    Page<Product> findProductsWithFilters(
            @Param("category") String category,
            @Param("inventoryStatus") InventoryStatus inventoryStatus,
            Pageable pageable
    );
    @Query("SELECT MAX(p.id) FROM Product p")
    Long findMaxId();

    Optional<Product> findByCode(String code);
}