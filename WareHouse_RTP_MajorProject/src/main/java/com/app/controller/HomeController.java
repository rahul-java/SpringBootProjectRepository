package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title","Warehouse : Home");
		return "Home";
	}
	
	@RequestMapping("/login")
	public String loginPage(Model model)
	{
		model.addAttribute("title","Warehouse : Login Page");
		return "login";
	}
}
