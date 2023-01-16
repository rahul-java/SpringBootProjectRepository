package com.app.user.service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.user.service.exception.ResourceNotFoundException;
import com.app.user.service.model.User;
import com.app.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		
		//generating random id in real time project
		String randomUid = UUID.randomUUID().toString();
		user.setUserId(randomUid);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public User getOneUser(String userId) {
		
		return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found On Server : "+userId));
	}

}
