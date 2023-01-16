package com.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Employee;
import com.app.model.repo.EmployeeRepository;

//@Component
public class InsertDataEmpRunner implements CommandLineRunner {

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Employee e1=new Employee(1,"Rahul",9000.0,"dev");
		Employee e2=new Employee(2,"Jyoti",7000.0,"dev");
		Employee e3=new Employee(3,"Shashikant",5000.0,"QA");
		Employee e4=new Employee(4,"Ashish",9000.0,"QA");
		Employee e5=new Employee(5,"Ravi",5000.0,"prod");
		Employee e6=new Employee(6,"Kuldeep",10000.0,"prod");
		Employee e7=new Employee(7,"Moolchand",6000.0,"dev");
		Employee e8=new Employee(8,"Gaurav",7000.0,"prod");
		Employee e9=new Employee(9,"Sanket",6000.0,"QA");
		
		empRepo.save(e1);
		empRepo.save(e2);
		empRepo.save(e3);
		empRepo.save(e4);
		empRepo.save(e5);
		empRepo.save(e6);
		empRepo.save(e7);
		empRepo.save(e8);
		empRepo.save(e9);
	}

}
