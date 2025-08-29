package com.alten.shop.utils.dtos.panier.input;

import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PanierRequestDTO (

        @NotNull(message = "Id is required")
        Long id,

        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Products are required")
        ProductPublicDTO product

){}
