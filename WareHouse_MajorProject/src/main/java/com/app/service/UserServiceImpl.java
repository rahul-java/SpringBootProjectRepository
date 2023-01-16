package com.app.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.UserNotFoundException;
import com.app.model.User;
import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.util.ListToMapConverter;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Integer saveUser(User User) {
		
		return userRepository.save(User).getId();
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public void updateUser(User User) {
		
		userRepository.save(User);

	}

	@Override
	public User getOneUser(Integer id) {
		
		return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User "+id+" does not exist !"));
	}

	@Override
	public void deleteUser(Integer id) {
		
		User User = getOneUser(id);
		userRepository.delete(User);
	}

	@Override
	public Map<Integer, String> getUserIdAndUserCode( String userType) {
		List<Object[]> userIdAndUserCode = userRepository.getUserIdAndUserCode(userType);
		Map<Integer,String> map = ListToMapConverter.converterListToMap(userIdAndUserCode);
		return map;
	}

	

}
