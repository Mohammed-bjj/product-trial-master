package com.alten.shop.api;

import com.alten.shop.services.product.ProductService;
import com.alten.shop.utils.dtos.product.input.ProductCreateRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductUpdateRequestDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import com.alten.shop.utils.mappers.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.alten.shop.utils.dtos.product.input.ProductSearchRequestDTO;
import jakarta.validation.Valid;
import com.alten.shop.utils.configuration.PaginationConfig;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductRestAPI {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final PaginationConfig paginationConfig;

    public ProductRestAPI(ProductService productService, ProductMapper productMapper, PaginationConfig paginationConfig) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.paginationConfig = paginationConfig;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponsePublicDTO>> searchProducts(
            @Valid ProductSearchRequestDTO searchRequest) {
        Pageable pageable = paginationConfig.createPageableForPublic(searchRequest.page(), searchRequest.size(), searchRequest.priceSort());
        Page<ProductResponsePublicDTO> products = productService.searchProductsForPublic(searchRequest, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<ProductResponseAdminDTO>  updateProduct(
            @PathVariable @Positive @NotNull(message = "ID is required")  Long id,
            @Valid @RequestBody ProductUpdateRequestDTO product) {
        ProductResponseAdminDTO productUpdated =  productService.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdated);
    }

    @GetMapping("/search/admin")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Page<ProductResponseAdminDTO>> searchProductsForAdmin(
            @Valid ProductSearchRequestDTO searchRequest) {
        Pageable pageable = paginationConfig.createPageableForAdmin(searchRequest.page(), searchRequest.size(), searchRequest.priceSort());
        Page<ProductResponseAdminDTO> products = productService.searchProductsForAdmin(searchRequest, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("/newProduct")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<ProductResponseAdminDTO> createProduct(@Valid @RequestBody ProductCreateRequestDTO productCreateRequestDTO) {
        ProductResponseAdminDTO createdProduct = productService.saveProduct(productCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Positive @NotNull(message = "ID is required")  Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/deleteAll")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAllProducts() {
        productService.deleteAllProduct();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/deleteAllInBatch")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAllProductsInBatch(@RequestBody List<Long> ids) {
        productService.deleteProductsInBatch(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}