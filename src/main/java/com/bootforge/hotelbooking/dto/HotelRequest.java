package com.bootforge.hotelbooking.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record HotelRequest(

        @NotBlank(message = "Name must be required")
        String name,

        @NotBlank(message = "Description must be required")
        String description,

        @NotBlank(message = "Location must be required")
        String location,

        @NotBlank(message = "City must be required")
        String city,

        @NotBlank(message = "State must be required")
        String state,

        @NotBlank(message = "State must be required")
        String country,

        @DecimalMin("0.0")
        @DecimalMax("5.0")
        BigDecimal rating
) {
}
