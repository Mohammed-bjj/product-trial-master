package com.alten.shop.services.product;

import com.alten.shop.dao.product.ProductRepository;
import com.alten.shop.services.user.AccountService;
import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import com.alten.shop.utils.mappers.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public void updateProduct(Long id, Product product) {

    }

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public Product getProductForPublic(Long id) {
        return null;
    }

    @Override
    public Product getProductForAdmin(Long id) {
        return null;
    }

    @Override
    public Page<ProductResponsePublicDTO> getProductsForPublic(Pageable pageable) {
      try {
            return productRepository.findAll(pageable)
                    .map(productMapper::toPublicDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products for public", e);
        }
    }

    @Override
    public Page<ProductResponseAdminDTO> getProductsForAdmin(Pageable pageable) {
        try {
            return productRepository.findAll(pageable)
                    .map(productMapper::toAdminDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products for admin", e);
        }
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return List.of();
    }

    @Override
    public List<Product> getFeaturedProducts(int limit) {
        return List.of();
    }
}
