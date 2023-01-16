package com.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.app.model.Student;

import lombok.Data;
@Data
@Component
@ConfigurationProperties( prefix= "com.app")
public class StudentRunner implements CommandLineRunner {

	Student student;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(student);
	}

}
