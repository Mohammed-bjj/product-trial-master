package com.alten.shop.utils.dtos.product.output;

public record ProductResponsePublicDTO (
        Long id,
        String name,
        String description,
        Double price,
        String image,
        String category,
        String inventoryStatus,
        Double rating
) { }