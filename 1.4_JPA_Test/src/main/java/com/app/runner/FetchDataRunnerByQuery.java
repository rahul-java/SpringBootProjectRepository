package com.app.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.repository.DeptRepository;
@Component
public class FetchDataRunnerByQuery implements CommandLineRunner {

	@Autowired
	DeptRepository deptRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		List<Object[]> l = deptRepo.getAllDataDepartmentAndEmp();
		for(Object[] ob:l)
			System.out.println(ob[0]+" || "+ob[1]+" || "+ob[2]+" || "+ob[3]+" || "+ob[4]+" || "+ob[5]);
	}

}
