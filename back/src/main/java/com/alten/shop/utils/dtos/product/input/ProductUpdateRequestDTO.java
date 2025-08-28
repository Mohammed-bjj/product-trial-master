package com.alten.shop.utils.dtos.product.input;

import com.alten.shop.utils.validators.NotAllFieldsEmpty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@NotAllFieldsEmpty(message = "At least one field must be provided for the partial update")
public record ProductUpdateRequestDTO(

        @Size(max = 50, message = "Code must not exceed 50 characters")
        String code,

        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description,

        String image,

        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        Double price,

        @Pattern(regexp = "Accessories|Electronics|Clothing|Fitness", message = "Invalid Category status")
        String category,

        @Min(value = 0, message = "Quantity must be 0 or greater")
        Integer quantity,

        String shellId,

        String internalReference,

        @Pattern(regexp = "INSTOCK|LOWSTOCK|OUTOFSTOCK", message = "Invalid inventory status")
        String inventoryStatus,

        @DecimalMin(value = "0.0", message = "Rating must be 0 or greater")
        @DecimalMax(value = "5.0", message = "Rating must be 5 or less")
        Double rating
) {
}
