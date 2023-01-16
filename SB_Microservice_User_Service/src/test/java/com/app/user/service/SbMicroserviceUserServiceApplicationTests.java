package com.app.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.app.user.service.external.service.RatingService;
import com.app.user.service.model.Rating;

@SpringBootTest
class SbMicroserviceUserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	
	@Test
	void contextLoads() {
	}

//	@Test
//	void createRating() {
//		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("this is created by using feign client").build();
//		ResponseEntity<Rating> createRating = ratingService.createRating(rating);
//		System.out.println("new rating created..."+createRating.getBody());
//	}
}
