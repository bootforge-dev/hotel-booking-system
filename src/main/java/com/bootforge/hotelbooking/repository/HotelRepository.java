package com.bootforge.hotelbooking.repository;

import com.bootforge.hotelbooking.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Page<Hotel> findByCityIgnoreCase(String city, Pageable pageable);
}
