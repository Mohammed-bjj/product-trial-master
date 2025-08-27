package com.alten.shop.api;

import com.alten.shop.services.product.ProductService;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import com.alten.shop.utils.mappers.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import org.springframework.validation.annotation.Validated;
import com.alten.shop.utils.dtos.product.input.ProductSearchRequestDTO;
import jakarta.validation.Valid;
import com.alten.shop.utils.configuration.PaginationConfig;

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


    @GetMapping
    public ResponseEntity<Page<ProductResponsePublicDTO>> getProducts(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponsePublicDTO> products = productService.getProductsForPublic(pageable);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Page<ProductResponseAdminDTO>> getProductsForAdmin(
            @Valid ProductSearchRequestDTO searchRequest) {
        Pageable pageable = paginationConfig.createPageableForAdmin(searchRequest.page(), searchRequest.size());
        Page<ProductResponseAdminDTO> products = productService.getProductsForAdmin(pageable);
        return ResponseEntity.status(200).body(products);
    }







}