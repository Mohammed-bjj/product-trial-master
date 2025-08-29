package com.alten.shop.utils.dtos.wishList.input;

import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import jakarta.validation.constraints.NotNull;

public record WishListRequestDTO (

        @NotNull(message = "Id is required")
        Long id,

        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Products are required")
        ProductPublicDTO product
)
{ }
