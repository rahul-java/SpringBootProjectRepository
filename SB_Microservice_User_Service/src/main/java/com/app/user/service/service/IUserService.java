package com.app.user.service.service;

import java.util.List;

import com.app.user.service.model.User;

public interface IUserService {

	 User saveUser(User user);
	 List<User> getAllUser();
	 User getOneUser(String userId);
	 
	 //TODO: delete
	 //TODO: update
}
