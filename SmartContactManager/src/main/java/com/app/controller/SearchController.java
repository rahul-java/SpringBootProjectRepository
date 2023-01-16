package com.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Contact;
import com.app.model.User;
import com.app.repository.IContactRepository;
import com.app.repository.IUserRepository;

@RestController
public class SearchController {

	@Autowired
	private IUserRepository userRepository ;
	@Autowired
	private IContactRepository contactRepository;
	
	//search handler
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query")String query ,Principal principal)
	{
		System.out.println(query);
		User user = this.userRepository.getUserByUserName(principal.getName());
        List<Contact> searchResult = this.contactRepository.findByNameContainingAndUser(query, user);
		return ResponseEntity.ok(searchResult);
	}
}
