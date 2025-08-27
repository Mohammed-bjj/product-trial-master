package com.alten.shop.dao.product;

import com.alten.shop.utils.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
