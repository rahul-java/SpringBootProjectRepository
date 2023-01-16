package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/prod")
public class HomeController {

	//@RequestMapping(value = "/index" ,method = RequestMethod.GET)
	@GetMapping("/show")
	public String indexPage()
	{
		return "index";
	}
	
	//@RequestMapping(value = "/save" , method = RequestMethod.POST)
	@PostMapping("/save")
	public String saveData()
	{
		return "";
	}
	
	//@PutMapping("/edit")
    @GetMapping("/edit")
	public String editRecord()
	{
		return "";
	}
}
