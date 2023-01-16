package com.app.runner;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Dept;
import com.app.repository.DeptRepository;

@Component
public class FetchDataRunner implements CommandLineRunner {

	@Autowired
	private DeptRepository deptRepo;
	
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		 Optional<Dept> record = deptRepo.findById(101);
		 System.out.println("Get data from DB :"+record);
	}

}
