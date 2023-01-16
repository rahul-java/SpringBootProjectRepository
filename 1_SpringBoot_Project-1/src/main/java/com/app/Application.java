package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.app.model.Employee;
import com.app.model.Student;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(Application.class, args);
		Employee emp = ctx.getBean("employee",Employee.class);
		Student std = ctx.getBean("std",Student.class);
		
		System.out.println(emp);
		System.out.println(std);
	}

}
