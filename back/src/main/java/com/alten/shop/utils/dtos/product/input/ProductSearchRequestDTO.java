package com.alten.shop.utils.dtos.product.input;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public record ProductSearchRequestDTO(
    
    @Size(min = 2, max = 100, message = "Search keyword must be between 2 and 100 characters")
    String keyword,
    
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Category contains invalid characters")
    String category,
    
    @DecimalMin(value = "0.0", message = "Min price must be 0 or greater")
    BigDecimal minPrice,
    
    @DecimalMin(value = "0.0", message = "Max price must be 0 or greater")
    BigDecimal maxPrice,
    
    @Pattern(regexp = "INSTOCK|LOWSTOCK|OUTOFSTOCK", message = "Invalid inventory status")
    String inventoryStatus,
    
    @Min(value = 0, message = "Page must be 0 or greater")
    Integer page,
    
    @Min(value = 1, message = "Size must be 1 or greater")
    @Max(value = 50, message = "Size must be 200 or less")
    Integer size
) { }