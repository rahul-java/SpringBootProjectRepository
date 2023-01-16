package com.app.rating.service.service;

import java.util.List;

import com.app.rating.service.model.Rating;

public interface RatingService {

	//create
	Rating createRating(Rating rating);
	
	//get all rating
	List<Rating> getAllRatings();
	
	//get all by userId
	List<Rating> getAllRatingsByUserId(String userId);
	
	//get all by hotel
	List<Rating> getAllRatingsByHotelId(String hotelId);

}
