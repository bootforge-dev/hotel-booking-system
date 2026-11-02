package com.bootforge.hotelbooking.service;

import com.bootforge.hotelbooking.dto.HotelRequest;
import com.bootforge.hotelbooking.dto.HotelResponse;
import com.bootforge.hotelbooking.dto.PageResponse;
import com.bootforge.hotelbooking.entity.Hotel;
import com.bootforge.hotelbooking.exception.ResourceNotFoundException;
import com.bootforge.hotelbooking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class HotelService {

    private final HotelRepository hotelRepository;

    @Transactional
    public HotelResponse createHotel(HotelRequest request) {
        Hotel hotel = Hotel.builder()
                .name(request.name())
                .description(request.description())
                .location(request.location())
                .city(request.city())
                .state(request.state())
                .country(request.country())
                .rating(request.rating())
                .build();

        Hotel savedHotel = hotelRepository.save(hotel);

        return toHotelResponse(savedHotel);
    }

    @Transactional(readOnly = true)
    public HotelResponse getHotelById(Long id) {
        Hotel hotel = getHotel(id);
        return this.toHotelResponse(hotel);
    }

    @Transactional(readOnly = true)
    public PageResponse<HotelResponse> getAllHotels(Pageable pageable) {

        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        List<HotelResponse> hotels = hotelPage.getContent().stream()
                .map(this::toHotelResponse)
                .toList();

        return new PageResponse<>(
                hotels,
                hotelPage.getNumber(),
                hotelPage.getSize(),
                hotelPage.getTotalElements(),
                hotelPage.getTotalPages()
        );
    }

    @Transactional
    public HotelResponse updateHotel(Long id, HotelRequest request) {
        Hotel hotel = getHotel(id);
        hotel.setName(request.name());
        hotel.setDescription(request.description());
        hotel.setLocation(request.location());
        hotel.setCity(request.city());
        hotel.setState(request.state());
        hotel.setCountry(request.country());
        hotel.setRating(request.rating());

        Hotel savedHotel = hotelRepository.save(hotel);

        return this.toHotelResponse(savedHotel);
    }


    @Transactional
    public void deleteHotel(Long id) {
        Hotel hotel = getHotel(id);
        hotelRepository.delete(hotel);
    }

    @Transactional(readOnly = true)
    public PageResponse<HotelResponse> findHotelsByCity(String city, Pageable pageable) {
        Page<Hotel> hotelPage = hotelRepository.findByCityIgnoreCase(city, pageable);
        List<HotelResponse> hotels = hotelPage.getContent().stream()
                .map(this::toHotelResponse).toList();

        return new PageResponse<>(
                hotels,
                hotelPage.getNumber(),
                hotelPage.getSize(),
                hotelPage.getTotalElements(),
                hotelPage.getTotalPages()
        );

    }

    private Hotel getHotel(Long id) {
        return hotelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Hotel not found!")
        );
    }

    private HotelResponse toHotelResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .location(hotel.getLocation())
                .city(hotel.getCity())
                .state(hotel.getState())
                .country(hotel.getCountry())
                .rating(hotel.getRating())
                .createdAt(hotel.getCreatedAt())
                .build();
    }
}
