package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	
	// all users
	@GetMapping("/")
	public List<User> getAllUsers()
	{
		return this.userService.getAllUsers();
	}
	
	//return single user
	@GetMapping("/{username}")
	public User getOneUser(@PathVariable("username") String username)
	{
		return this.userService.getOneUser(username);
	}
	
	//Add User
	@PostMapping("/add")
	public User addUser(@RequestBody User user)
	{
		return this.userService.addUser(user);
	}
	
	
	
}
