package com.app.hotel.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.hotel.service.model.Hotel;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

	
}
