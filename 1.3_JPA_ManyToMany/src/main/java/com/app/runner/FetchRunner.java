package com.app.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Student;

import com.app.model.repo.StudentRepository;

@Component
public class FetchRunner implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public void run(String... args) throws Exception {

		List<Student> findAll = studentRepository.findAll();
		for(Student std :findAll)
		{
			//System.out.println(std.toString());
			System.out.println(std.getSname());
			System.out.println(std.getSid()+" || "+std.getSname()+" || "+std.getSadd());
		
		}
		
	}

}
