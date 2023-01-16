package com.app.controller;




import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.model.Student;
@Controller
public class MyController {

	@GetMapping("/")
	public String showName(Model model)
	{
		String str="Rahul Kumar Pandey";
		Student student=new Student();
		student.setId(101);
		student.setName("Rahul Pandey");
		student.setCourse("SpringBoot");
		student.setFee(5.0);
		model.addAttribute("name", str);
		model.addAttribute("std", student);
		return "home";
	}
	@GetMapping("/student")
	public String showStudent(Model model)
	{
		
		List<Student> students=List.of(new Student(101, "A", "a", 1.0),
				                       new Student(102, "B", "b", 1.0),
				                       new Student(103, "C", "c", 1.0),
				                       new Student(104, "D", "d", 1.0),
				                       new Student(105, "E", "e", 1.0),
				                       new Student(106, "F", "f", 1.0));
			
		
		List<Student> studList=new ArrayList<Student>();
		Student stdudent1= new Student(101, "A", "a", 1.0);
		Student stdudent2= new Student(102, "B", "b", 1.0);
		Student stdudent3= new Student(103, "C", "c", 1.0);
		Student stdudent4= new Student(104, "D", "d", 1.0);
		Student stdudent5= new Student(105, "E", "e", 1.0);
		Student stdudent6=new Student(106, "F", "f", 1.0);
		studList.add(stdudent1);
		studList.add(stdudent2);
		studList.add(stdudent3);
		studList.add(stdudent4);
		studList.add(stdudent5);
		studList.add(stdudent6);
		model.addAttribute("students", studList);
				                       
		
		
		return "student";
	}
}
