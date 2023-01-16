package com.app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.entity.Student;
import com.app.repository.StudentRepository;



@SpringBootApplication
public class SpringBootJpaMappingPostgreApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaMappingPostgreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Student student=new Student();
		student.setName("RkP");
		student.setId(101);
		student.setAddress("UP");
		
		Student save = studentRepository.save(student);
		System.out.println("Student Saved : "+save);

	}

}
