package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Student;
import com.app.service.IStudentService;

@Controller
public class StudentController {

	@Autowired
	private IStudentService service;
	
	@GetMapping("/")
	public String showIndex(Model model)
	{
		Student student=new Student();
		model.addAttribute("student", student);
		return "index";
	}
	
	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student,Model model)
	{
		Integer stdId=student.getId();
		Integer sid = service.saveStudent(student);
		if(sid>0)
		{
			if(stdId!=null)
				model.addAttribute("sid", "Student "+sid+" is Updated successfully !");
			else 
				model.addAttribute("sid", "Student "+sid+" is added successfully !");
		}
		else {
			model.addAttribute("sid", "Student "+sid+" is not created !");
		}
		
		return "index";
	}
	
	@GetMapping("/all")
	public String getAllStudentData(Model model)
	{
		List<Student> allStudents=service.getAllStudent();
		model.addAttribute("students", allStudents);
		return "studentsData";
	}
	
	@GetMapping("/edit")
	public String editStudent(@RequestParam("id") Integer id,Model model)
	{
		Student student = service.getStudentById(id);
		model.addAttribute("student", student);
		return "edit";
	}
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("id") Integer id, Model model)
	{
		service.deleteStudentById(id);
		/*
		List<Student> allStudents = service.getAllStudent();
		model.addAttribute("students", allStudents);
		return "studentsData";
		*/
		return "redirect:all";
	}
}
