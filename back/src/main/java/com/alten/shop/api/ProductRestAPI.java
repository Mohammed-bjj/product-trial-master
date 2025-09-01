package com.alten.shop.api;

import com.alten.shop.services.product.ProductService;
import com.alten.shop.utils.dtos.product.input.ProductCreateRequestDTO;
import com.alten.shop.utils.dtos.product.input.ProductUpdateRequestDTO;
import com.alten.shop.utils.dtos.product.output.ProductAdminDTO;
import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Search products for public")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found"),
            @ApiResponse(responseCode = "404", description = "Products not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<ProductPublicDTO>> searchProducts(
            @Valid ProductSearchRequestDTO searchRequest) {
        Pageable pageable = paginationConfig.createPageableForPublic(searchRequest.page(), searchRequest.size(), searchRequest.priceSort());
        Page<ProductPublicDTO> products = productService.searchProductsForPublic(searchRequest, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PatchMapping("admin/update/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<ProductAdminDTO>  updateProduct(
            @PathVariable @Positive @NotNull(message = "ID is required")  Long id,
            @Valid @RequestBody ProductUpdateRequestDTO product) {
        ProductAdminDTO productUpdated =  productService.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdated);
    }

    @Operation(summary = "Search products for admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found"),
            @ApiResponse(responseCode = "404", description = "Products not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("/admin/search")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Page<ProductAdminDTO>> searchProductsForAdmin(
            @Valid ProductSearchRequestDTO searchRequest) {
        Pageable pageable = paginationConfig.createPageableForAdmin(searchRequest.page(), searchRequest.size(), searchRequest.priceSort());
        Page<ProductAdminDTO> products = productService.searchProductsForAdmin(searchRequest, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/admin/newProduct")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<ProductAdminDTO> createProduct(@Valid @RequestBody ProductCreateRequestDTO productCreateRequestDTO) {
        ProductAdminDTO createdProduct = productService.saveProduct(productCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @Operation(summary = "Delete one product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/admin/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Positive @NotNull(message = "ID is required")  Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @Operation(summary = "Delete all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Products deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @DeleteMapping("/admin/deleteAll")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAllProducts() {
        productService.deleteAllProduct();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Delete all products in batch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Products deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/admin/deleteAllInBatch")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAllProductsInBatch(@RequestBody List<Long> ids) {
        productService.deleteProductsInBatch(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}