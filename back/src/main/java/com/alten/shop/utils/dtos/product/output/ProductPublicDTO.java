package com.alten.shop.utils.dtos.product.output;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProductPublicDTO(
        @NotNull(message = "Id is required")
        Long id,

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "description is required")
        String description,

        @NotNull(message = "price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        Double price,

        @NotBlank(message = "image is required")
        String image,

        @NotBlank(message = "category is required")
        @Pattern(regexp = "Accessories|Electronics|Clothing|Fitness", message = "Invalid Category status")
        String category,

        @NotNull(message = "quantity is required")
        Integer quantity,

        @NotBlank(message = "internalReference is required")
        String internalReference,

        @NotNull(message = "inventoryStatus is required")
        @Pattern(regexp = "INSTOCK|LOWSTOCK|OUTOFSTOCK", message = "Invalid inventory status")
        String inventoryStatus,

        @NotNull(message = "rating is required")
        Double rating
) { }