package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {

	//home page
		@GetMapping("/home")
		public String home()
		{
			return "this is home page ......";
		}
		
		@GetMapping("/login")
		public String login()
		{
			return "this is login page ......";
		}
		
		@GetMapping("/regist")
		public String register()
		{
			return "this is register page ......";
		}
		
}
