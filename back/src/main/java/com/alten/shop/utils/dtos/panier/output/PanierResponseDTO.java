package com.alten.shop.utils.dtos.panier.output;

import com.alten.shop.utils.dtos.product.output.ProductPublicDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PanierResponseDTO (

         @NotNull(message = "Products are required")
         ProductPublicDTO product

) { }
