package com.app.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Department;
import com.app.model.repository.DeptRepository;
//@Component
public class FetchRunner implements CommandLineRunner {

	@Autowired
	DeptRepository deptRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		List<Department> findAllList = deptRepo.findAll();
		for( Department d:findAllList)
		{
			System.out.println(d.getDCode()+"\t"+d.getDName()+"\t"+d.getId());
		}

	}

}
