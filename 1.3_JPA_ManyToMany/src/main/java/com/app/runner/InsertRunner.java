package com.app.runner;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Course;
import com.app.model.Student;
import com.app.model.repo.CourseRepository;
import com.app.model.repo.StudentRepository;

//@Component
public class InsertRunner implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Course c1=new Course(10,"CoreJava",10.0);
		Course c2=new Course(11,"SpringBoot",10.0);
		Course c3=new Course(12,"MicroService",10.0);
		Course c4=new Course(13,"Angular",10.0);
		
		Student s1=new Student(101,"Amit","UP",Arrays.asList(c1,c2,c3));
		Student s2=new Student(102,"Akash","MP",Arrays.asList(c2,c4));
		Student s3=new Student(103,"Abhishek","AP",Arrays.asList(c1,c2,c4));
		Student s4=new Student(101,"Tamhid","BH",Arrays.asList(c2));
		
		courseRepository.save(c1);
		courseRepository.save(c2);
		courseRepository.save(c3);
		courseRepository.save(c4);
		
		studentRepository.save(s1);
		studentRepository.save(s2);
		studentRepository.save(s3);
		studentRepository.save(s4);
		
	}

}
