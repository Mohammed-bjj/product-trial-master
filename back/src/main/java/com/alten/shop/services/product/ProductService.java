package com.alten.shop.services.product;

import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.security.core.Authentication;


public interface ProductService {
    public void deleteProduct(Long id);
    public void updateProduct(Long id, Product product);
    public void createProduct(Product product);
    // Accès public
    public Product getProductForPublic(Long id);
    
    // Accès admin  
    public Product getProductForAdmin(Long id);
    
    // Pagination
    public Page<ProductResponsePublicDTO> getProductsForPublic(Pageable pageable);
    public Page<ProductResponseAdminDTO> getProductsForAdmin(Pageable pageable);
    
    // Filtrage
    public List<Product> getProductsByCategory(String category);
    public List<Product> searchProducts(String keyword);
    
    // Limité
    public List<Product> getFeaturedProducts(int limit);



}
