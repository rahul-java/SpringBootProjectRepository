package com.app.hotel.service.service;

import java.util.List;

import com.app.hotel.service.model.Hotel;

public interface HotelService {

	//Create
	Hotel createHotel(Hotel hotel);
	//Get ALL
	List<Hotel> getALLHotels();
	//Get One
	Hotel getOneHotel(String id);
}
