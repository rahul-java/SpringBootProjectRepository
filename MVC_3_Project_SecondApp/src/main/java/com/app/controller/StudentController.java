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
	public String loadForm()
	{
		return "index";
	}
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Student student, Model model)
	{
		System.out.println(student);
		model.addAttribute("std",student);
		return "viewData";
	}
}
