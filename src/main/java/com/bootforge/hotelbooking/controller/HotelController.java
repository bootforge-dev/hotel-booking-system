package com.bootforge.hotelbooking.controller;

import com.bootforge.hotelbooking.dto.HotelRequest;
import com.bootforge.hotelbooking.dto.HotelResponse;
import com.bootforge.hotelbooking.dto.PageResponse;
import com.bootforge.hotelbooking.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody @Valid HotelRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hotelService.createHotel(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<HotelResponse>> getAllHotels(
            @ParameterObject
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return ResponseEntity.ok(hotelService.getAllHotels(pageable));
    }

    @GetMapping("/city")
    public ResponseEntity<PageResponse<HotelResponse>> getHotelsByCity(
            @RequestParam String city,
            @ParameterObject
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
            return ResponseEntity
                    .ok(hotelService.findHotelsByCity(city, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable Long id, @RequestBody @Valid HotelRequest request) {
        return ResponseEntity.ok(hotelService.updateHotel(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.accepted().build();

    }

}
