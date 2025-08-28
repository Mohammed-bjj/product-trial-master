package com.alten.shop.services.product;

import com.alten.shop.utils.dtos.product.input.ProductCreateRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductSearchRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductUpdateRequestDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.exceptions.Uncheck.product.ProductAlreadyExistException;
import com.alten.shop.utils.exceptions.Uncheck.product.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {
    public void deleteProduct(Long id) throws ProductNotFoundException;
    public void deleteProductsInBatch(List<Long>  ids) throws ProductNotFoundException, RuntimeException;
    public void deleteAllProduct() throws RuntimeException;
    public ProductResponseAdminDTO updateProduct(Long id, ProductUpdateRequestDTO product) throws ProductNotFoundException, RuntimeException;
    public ProductResponseAdminDTO saveProduct(ProductCreateRequestDTO product) throws ProductAlreadyExistException;

    // Search with pagination and filters
    public Page<ProductResponsePublicDTO> searchProductsForPublic(ProductSearchRequestDTO searchRequest, Pageable pageable);
    public Page<ProductResponseAdminDTO> searchProductsForAdmin(ProductSearchRequestDTO searchRequest, Pageable pageable);




}
