package com.alten.shop.utils.dtos.product.input;

import jakarta.validation.constraints.*;

import java.time.Instant;

public record ProductCreateRequestDTO (
    
    @NotBlank(message = "Code is required")
    String code,
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name,
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description,

    @NotBlank(message = "Image is required")
    String image,
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    Double price,
    
    @NotBlank(message = "Category is required")
    @Pattern(regexp = "Accessories|Electronics|Clothing|Fitness", message = "Invalid Category status")
    String category,
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be 0 or greater")
    Integer quantity,

    @NotNull(message = "Shell ID is required")
    Integer shellId,

    @NotBlank(message = "Internal reference is required")
    String internalReference,
    
    @NotBlank(message = "Inventory status is required")
    @Pattern(regexp = "INSTOCK|LOWSTOCK|OUTOFSTOCK", message = "Invalid inventory status")
    String inventoryStatus,
    
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    Integer rating


) {}