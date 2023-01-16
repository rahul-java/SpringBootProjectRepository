package com.app.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.app.model.User;

public interface UserService {

	Integer saveUser(User user);
	
   // UserDetails findByUserName(String userName);
}
