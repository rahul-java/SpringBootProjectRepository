package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.User;
import com.app.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String showRegistrationPage(Model model)
	{
		User user=new User();
		model.addAttribute("user",user);
		return "userRegistration";
	}
	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user,Model model)
	{
		Integer id = userService.saveUser(user);
		model.addAttribute("successMsg","User "+ id +" saved successfully...");
		return "userRegistration";
	}
}
