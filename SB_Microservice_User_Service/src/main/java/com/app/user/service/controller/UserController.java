package com.app.user.service.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.user.service.external.service.HotelService;
import com.app.user.service.model.Hotel;
import com.app.user.service.model.Rating;
import com.app.user.service.model.User;
import com.app.user.service.service.IUserService;
import com.app.user.service.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User saveUser = userService.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
		
	}
	
	//get one user by id
	@GetMapping("/{userId}")
	public ResponseEntity<User> getOneUser(@PathVariable("userId") String userId){
		
		//fetching user from user repository
		User oneUser = userService.getOneUser(userId);
		
		//fetching ratings from Rating Service
		//http://localhost:8093/ratings/users/64c77f70-fac2-4e47-b8c0-232d1e4ad780
		Rating[] ratingsOfUSer = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+oneUser.getUserId(), Rating[].class);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUSer).toList();
		logger.info("{} ",ratings);
		
		List<Rating> ratingList = ratings.stream().map(rating->{
			//api call to hotel service to get the hotel
			//url:http://localhost:8092/hotels/597a713a-c681-448a-9502-7a5e0bca65a1
			
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			//Hotel hotel = forEntity.getBody();
		
			//logger.info("response status code : {} ",forEntity.getStatusCode());
			
			//by using FeignClient
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			
			//set the hotel to rating
			
			rating.setHotel(hotel);
			
			//return the rating
			
			return rating;
		}).collect(Collectors.toList());
		
		oneUser.setRatings(ratingList);
		return ResponseEntity.ok(oneUser);
		
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		
		List<User> allUser = userService.getAllUser();
		//Implementing Rating Service Call : Using REST Template
		
		return ResponseEntity.ok(allUser);
		
	}
}
