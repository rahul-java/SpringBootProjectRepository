package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Student;
import com.app.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("/add")
	public String showRegForm(Student student)
	{
		return "add-student";
	}
	
	@GetMapping("/all")
	public String showAllStudents(Model model)
	{
		List<Student> allStudents = studentService.getAllStudents();
		model.addAttribute("students", allStudents);
		return "index";
	}
	
	@PostMapping("/save")
	public String addStudent(@Validated Student student,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return "add-student";
		}
		this.studentService.saveStudent(student);
		return "redirect:all";
	}
	
	@PostMapping("/update/{id}")
	public String updateStudent(@PathVariable("id") long id,@Validated Student student,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			student.setId(id);
			return "update-student";
		}
		studentService.updateStudent(student);
		model.addAttribute("students",studentService.getAllStudents());
		return "index";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id,Model model)
	{
		Student oneStudent = studentService.getOneStudent(id);
		model.addAttribute("student", oneStudent);
		
		return "update-student";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") long id,Model model)
	{
		studentService.deleteStudent(id);
		model.addAttribute("students",studentService.getAllStudents());
		return "index";
	}

}
