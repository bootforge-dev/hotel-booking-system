package com.bootforge.hotelbooking.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record HotelResponse(
        Long id,
        String name,
        String description,
        String location,
        String city,
        String state,
        String country,
        BigDecimal rating,
        LocalDateTime createdAt
) {
}
