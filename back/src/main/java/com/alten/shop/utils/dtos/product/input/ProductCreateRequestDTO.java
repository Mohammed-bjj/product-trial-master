package com.alten.shop.utils.dtos.product.input;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductCreateRequestDTO(
    
    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    String code,
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name,
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description,
    
    String image,
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    BigDecimal price,
    
    @NotBlank(message = "Category is required")
    String category,
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be 0 or greater")
    Integer quantity,
    
    String shellId,
    
    String internalReference,
    
    @NotBlank(message = "Inventory status is required")
    @Pattern(regexp = "INSTOCK|LOWSTOCK|OUTOFSTOCK", message = "Invalid inventory status")
    String inventoryStatus,
    
    @DecimalMin(value = "0.0", message = "Rating must be 0 or greater")
    @DecimalMax(value = "5.0", message = "Rating must be 5 or less")
    BigDecimal rating
) {}