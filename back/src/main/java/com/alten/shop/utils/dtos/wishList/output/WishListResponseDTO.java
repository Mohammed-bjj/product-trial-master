package com.alten.shop.utils.dtos.wishList.output;

import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import jakarta.validation.constraints.NotNull;

public record WishListResponseDTO(

        @NotNull(message = "Products are required")
        ProductPublicDTO product
) { }
