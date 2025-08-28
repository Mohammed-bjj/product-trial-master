package com.alten.shop.utils.dtos.panier.input;

public record PanierRequestDTO (

        @NotNull(message = "L'ID du produit est obligatoire")
        Long productId,

        @NotNull(message = "La quantité est obligatoire")
        Integer quantity
) {
}
