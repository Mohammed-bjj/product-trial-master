package com.alten.shop.utils.dtos.user.output;


import jakarta.validation.constraints.NotBlank;



public record LoginResponseDTO(
        @NotBlank(message = "Token is required")
        String token )
{}
