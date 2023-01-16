package com.app.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Department;
import com.app.model.Employee;
import com.app.model.repository.DeptRepository;
import com.app.model.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//@Component

public class InsertDataRunner implements CommandLineRunner {

	@Autowired
	DeptRepository deptRepo;
	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Employee e1=new Employee(1, "Akash", "Alld");
		Employee e2=new Employee(2, "Dilip", "Delhi");
		Employee e3=new Employee(3, "Bhuwan", "Bengaluru");
		Employee e4=new Employee(4, "Kavi", "Kanpur");
		
		empRepo.save(e1);
		empRepo.save(e2);
		empRepo.save(e3);
		empRepo.save(e4);
		
		Department d1=new Department(101,"Development","dev",Arrays.asList(e1,e2));
		Department d2=new Department(102,"Development","dev",Arrays.asList(e3,e4));
		
		deptRepo.save(d1);
		deptRepo.save(d2);

	}

}
