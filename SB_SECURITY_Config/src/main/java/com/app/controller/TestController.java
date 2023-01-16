package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/welcome")
	public String welcomePage()
	{
		return "registration";
	}
	
	@GetMapping("/home")
	public String homePage()
	{
		return "home";
	}
	
	@GetMapping("/viewBalance")
	public String viewBalancePage()
	{
		return "viewBalance";
	}
	
	@GetMapping("/activateUser")
	public String activateUserPage()
	{
		return "activateUser";
	}
	
	@GetMapping("/view")
	public String viewPage()
	{
		return "view";
	}
}
