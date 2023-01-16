package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.model.Student;

@Controller
public class StudentController {

	@GetMapping("/")
	public String indexPage(Model model)
	{
		model.addAttribute("stuId", 101);
		model.addAttribute("stuName", "Rahul");
		return "index";
	}
	
	@GetMapping("/student")
	public String studentPage(@ModelAttribute Student student)
	{
		return "student";
	}
	@PostMapping("/save")
	public String showStudent(@ModelAttribute Student student)
	{
		System.out.println(student);
		return "save";
	}
}
