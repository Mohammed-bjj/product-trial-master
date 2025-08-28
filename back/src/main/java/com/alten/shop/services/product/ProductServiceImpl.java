package com.alten.shop.services.product;

import com.alten.shop.dao.product.ProductRepository;
import com.alten.shop.utils.dtos.product.input.ProductCreateRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductSearchRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductUpdateRequestDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.entities.product.Product;
import com.alten.shop.utils.entities.product.InventoryStatus;
import com.alten.shop.utils.exceptions.Uncheck.product.ProductAlreadyExistException;
import com.alten.shop.utils.exceptions.Uncheck.product.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.alten.shop.utils.mappers.ProductMapper;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException, RuntimeException {
        Product productExisted = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        try{
            productRepository.delete(productExisted);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete one product with id");
        }
    }


    @Override
    public void deleteProductsInBatch(List<Long> ids) throws ProductNotFoundException, RuntimeException {
            ids.forEach(id -> {
                Product productExisted = productRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException("Product not found"));
                try{
                    productRepository.delete(productExisted);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to delete batch product with ids");
                }
            });
    }

    @Override
    public void deleteAllProduct() throws RuntimeException {
          try {
              productRepository.deleteAll();
          }catch (Exception e){
              throw new RuntimeException("Failed to delete all products or Data base is empty");
          }
    }

    @Override
    public ProductResponseAdminDTO updateProduct(Long id, ProductUpdateRequestDTO dto) {
            return  productRepository.findById(id)
                    .map( (productExisted )  ->  {
                                Product product = productMapper.toEntity(dto, productExisted);
                                return Optional.of(productRepository.save(product))
                                        .map(productMapper::toAdminDTO)
                                        .orElseThrow( () -> new RuntimeException("Failed to update product"));
                    })
                    .orElseThrow( () -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public ProductResponseAdminDTO saveProduct(ProductCreateRequestDTO p) throws ProductAlreadyExistException {

        return (ProductResponseAdminDTO) productRepository.findByCode(p.code())
                .map(product -> {
                    throw new ProductAlreadyExistException("Product already exists");
                })
                .orElseGet(() -> {
                    Product product = productMapper.toEntity(p);
                   return Optional.of(productRepository.save(product))
                            .map(productMapper::toAdminDTO)
                            .orElseThrow(() -> new RuntimeException("Failed to save product"));
                });
    }


    @Override
    public Page<ProductResponsePublicDTO> searchProductsForPublic(ProductSearchRequestDTO searchRequest, Pageable pageable) {
        try {

            String category = searchRequest.category();
            Page<Product> products = productRepository.findProductsWithFilters(
                category, null, pageable
            );
            return products.map(productMapper::toPublicDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search products for public: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductResponseAdminDTO> searchProductsForAdmin(ProductSearchRequestDTO searchRequest, Pageable pageable) {
        try {
            String category = searchRequest.category();
            InventoryStatus inventoryStatus = searchRequest.inventoryStatus() == null ? null : InventoryStatus.valueOf(searchRequest.inventoryStatus());
            Page<Product> products = productRepository.findProductsWithFilters(
                category, inventoryStatus, pageable
            );
            return products.map(productMapper::toAdminDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search products for admin: " + e.getMessage(), e);
        }
    }
}