package com.app.hotel.service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.hotel.service.model.Hotel;
import com.app.hotel.service.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	//create
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
		
	}
	
	//getOneHotel
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getOneHotel(@PathVariable("hotelId") String hotelId)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getOneHotel(hotelId));
		
	}
	
	//Get All Hotels
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels()
	{
		return ResponseEntity.ok(hotelService.getALLHotels());
		
	}
}
