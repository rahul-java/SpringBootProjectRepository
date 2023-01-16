package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/")
	public String showUser(Model model)
	{
		User User=new User();
		model.addAttribute("User" , User);
		model.addAttribute("title","Warehouse : User");
		return "UserRegistration";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("User") User User,Model model)
	{
		Integer id = userService.saveUser(User);
		model.addAttribute("msg",id);
		model.addAttribute("User" , new User());
		return "UserRegistration";
	}
	
	@GetMapping("/all")
	public String getAllUser(Model model)
	{
		List<User> allUser = userService.getAllUser();
		model.addAttribute("list",allUser);
		model.addAttribute("title","Warehouse : User");
		return "UserData";
	}
	
	@GetMapping("/edit")
	public String editUser(@RequestParam("id") Integer id,Model model)
	{
		User oneUser = userService.getOneUser(id);
		model.addAttribute("User",oneUser);
		model.addAttribute("title","Warehouse : User");
		return "UserEdit";
	}
	
	@PostMapping("/update")
	public String updateUser(@ModelAttribute User User,Model model)
	{
		userService.updateUser(User);
		model.addAttribute("list",userService.getAllUser());
		return "UserData";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") Integer id,Model model)
	{
		userService.deleteUser(id);
		model.addAttribute("list",userService.getAllUser());
		return "UserData";
	}
	
}
