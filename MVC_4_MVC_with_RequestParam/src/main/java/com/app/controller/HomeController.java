package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String showView(@RequestParam("id") Integer id,
			               @RequestParam(value = "uname") String name,
			               @RequestParam(required = false, defaultValue = "Noida") String add,
			               Model model)
	{
		System.out.println("id : "+id);
		System.out.println("name : "+name);
		System.out.println("add : "+add);
		model.addAttribute("uid", id);
		model.addAttribute("uname", name);
		model.addAttribute("uadd", add);
		return "index";
	}
}
