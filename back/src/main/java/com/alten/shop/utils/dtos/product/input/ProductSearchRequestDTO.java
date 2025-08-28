package com.alten.shop.utils.dtos.product.input;

import jakarta.validation.constraints.*;

public record ProductSearchRequestDTO(


    @Pattern(regexp = "Accessories|Electronics|Clothing|Fitness", message = "Invalid Category status")
    String category,

    @Pattern(regexp = "INSTOCK|LOWSTOCK|OUTOFSTOCK", message = "Invalid inventory status")
    String inventoryStatus,

    @Pattern(regexp = "ASC|DESC", message = "Price sort must be ASC or DESC")
    String priceSort,

    @Min(value = 0, message = "Page must be 0 or greater")
    Integer page,

    @Min(value = 1, message = "Size must be 1 or greater")
    @Max(value = 50, message = "Size must be 200 or less")
    Integer size
) { }