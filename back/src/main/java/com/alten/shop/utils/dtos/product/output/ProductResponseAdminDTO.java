package com.alten.shop.utils.dtos.product.output;

import jakarta.validation.constraints.*;

import java.time.Instant;

public record ProductResponseAdminDTO(

        @NotNull(message = "Id is required")
        @Positive(message = "ID must be a positive number")
        Long id,

        @NotBlank(message = "Code is required")
        String code,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Price is required")
        Double price,

        @NotBlank(message = "Image is required")
        String image,

        @NotBlank(message = "Internal reference is required")
        String internalReference,

        @NotNull(message = "Shell ID is required")
        Long shellId,

        @NotBlank(message = "Category is required")
        @Pattern(regexp = "Accessories|Electronics|Clothing|Fitness", message = "Invalid category")
        String  category,

        @NotNull(message = "Quantity is required")
        @Min(value = 0, message = "Quantity must be 0 or greater")
        Integer quantity,

        @NotBlank(message = "Inventory status is required")
        @Pattern(regexp = "INSTOCK|LOWSTOCK|OUTOFSTOCK", message = "Invalid inventory status")
        String inventoryStatus,

        @NotNull(message = "Rating is required")
        @Min(value = 1, message = "Rating must be between 1 and 5")
        @Min(value = 5, message = "Rating must be between 1 and 5")
        Integer rating,

        @NotNull(message = "Created at is required")
        Instant createdAt,

        @NotNull(message = "Updated at is required")
        Instant updatedAt
) {
}
