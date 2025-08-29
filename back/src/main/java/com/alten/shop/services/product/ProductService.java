package com.alten.shop.services.product;

import com.alten.shop.utils.dtos.product.input.ProductCreateRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductSearchRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductUpdateRequestDTO;
import com.alten.shop.utils.dtos.product.output.ProductAdminDTO;
import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import com.alten.shop.utils.entities.product.Product;
import com.alten.shop.utils.exceptions.Uncheck.product.ProductAlreadyExistException;
import com.alten.shop.utils.exceptions.Uncheck.product.ProductNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public void deleteProduct(Long id) throws ProductNotFoundException;
    public void deleteProductsInBatch(List<Long>  ids) throws ProductNotFoundException, RuntimeException;
    public void deleteAllProduct() throws RuntimeException;
    public ProductAdminDTO updateProduct(Long id, ProductUpdateRequestDTO product) throws ProductNotFoundException, RuntimeException;
    public ProductAdminDTO saveProduct(ProductCreateRequestDTO product) throws ProductAlreadyExistException;

    // Search with pagination and filters
    public Page<ProductPublicDTO> searchProductsForPublic(ProductSearchRequestDTO searchRequest, Pageable pageable);
    public Page<ProductAdminDTO> searchProductsForAdmin(ProductSearchRequestDTO searchRequest, Pageable pageable);


    Optional<Product> findById(@NotNull(message = "Id is required") Long id);

}
