package com.app.service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public Integer saveUser(User user) {
		String userPassword = user.getUserPassword();
		String encodeedPassword = encoder.encode(userPassword);
		user.setUserPassword(encodeedPassword);
		return userRepository.save(user).getId();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optional = userRepository.findByUserName(username);
		if(optional.isEmpty())
		{
			throw new UsernameNotFoundException("User is not available ! ");
		}
		else 
		{
			User user = optional.get();
			Set<String> roles = user.getRoles();
			Set<GrantedAuthority> authorities=new HashSet<>();
			for(String role:roles)
				authorities.add(new SimpleGrantedAuthority(role));
			return new org.springframework.security.core.userdetails.User(username, user.getUserPassword() , authorities);
		}
		
	}

}
