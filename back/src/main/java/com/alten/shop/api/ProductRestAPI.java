package com.alten.shop.api;

import com.alten.shop.services.product.ProductService;
import com.alten.shop.utils.dtos.IdRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductCreateRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductUpdateRequestDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponsePublicDTO;
import com.alten.shop.utils.dtos.product.output.ProductResponseAdminDTO;

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
    public ResponseEntity<ProductResponsePublicDTO>  updateProduct(
            @Valid @PathVariable IdRequestDTO id,
            @Valid @RequestBody ProductUpdateRequestDTO product) {
        productService.updateProduct(id.id(), product);
        return ResponseEntity.status(HttpStatus.OK).build();
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

}