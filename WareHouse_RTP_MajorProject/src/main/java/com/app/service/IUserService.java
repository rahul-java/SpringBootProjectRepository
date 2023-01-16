package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.model.User;

public interface IUserService {

	public Integer saveUser(User User);
	public List<User> getAllUser();
	public void updateUser(User User);
	public User getOneUser(Integer id);
	public void deleteUser(Integer id);
	
	public Map<Integer, String> getUserIdAndUserCode(String userType);
}
