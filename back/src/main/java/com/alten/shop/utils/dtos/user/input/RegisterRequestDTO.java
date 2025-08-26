package com.alten.shop.utils.dtos.user.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;



public record RegisterRequestDTO (

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
     String email,

    @NotBlank(message = "Password is required")
     String password,

    @NotBlank(message = "First name is required")
     String firstName,

    @NotBlank(message = "Last name is required")
     String lastName)
{}
