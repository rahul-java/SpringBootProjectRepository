package com.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.repo.EmployeeRepository;
import com.app.model.repo.FetchCrudRepo;
@Component
public class FetchDataEmpRunner implements CommandLineRunner {

	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	FetchCrudRepo empCrud;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		//empRepo.findBysal(10000.0).forEach(System.out::println);
		
		//empRepo.findByDept("dev").forEach(System.out::println);
		
		//empRepo.findBySalLessThan(6000.0).forEach(System.out::println);
		
		//empRepo.findByDeptNot("QA").forEach(System.out::println);
		
		//empRepo.findBySalGreaterThanEqualAndDeptNot(5000.0, "QA").forEach(System.out::println);
		
		//empRepo.findByNameLike("s%").forEach(System.out::println);
		
		//empRepo.findByNameStartingWith("R").forEach(System.out::println);
	
		empCrud.findAll().forEach(System.out::println);
		
	}

}
