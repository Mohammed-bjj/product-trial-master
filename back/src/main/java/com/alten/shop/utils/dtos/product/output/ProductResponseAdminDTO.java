package com.alten.shop.utils.dtos.product.output;

public record ProductResponseAdminDTO(
        Long id,
        String code,
        String name,
        String description,
        Double price,
        String image,
        String internalReference,
        Long shellId,
        String  category,
        Integer quantity,
        String inventoryStatus,
        Double rating,
        Long createdAt,
        Long updatedAt
) {
}
