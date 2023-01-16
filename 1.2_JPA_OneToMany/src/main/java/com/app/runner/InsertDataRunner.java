package com.app.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Dept;
import com.app.model.Emp;
import com.app.repository.DeptRepository;
import com.app.repository.EmpRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//@Component
public class InsertDataRunner implements CommandLineRunner {

	@Autowired
	DeptRepository deptRepo;
	@Autowired
	EmpRepository empRepo;
	@Override
	public void run(String... args) throws Exception {
		
     Emp e1=new Emp(1,"Amit",9.8);
     Emp e2=new Emp(2,"Shubham",6.8);
     Emp e3=new Emp(3,"Vikash",7.8);
     Emp e4=new Emp(4,"Amitabh",10.8);
     Emp e5=new Emp(5,"Amitesh",11.8);
     Emp e6=new Emp(6,"Priya",19.8);
     
     empRepo.save(e1);
     empRepo.save(e2);
     empRepo.save(e3);
     empRepo.save(e4);
     empRepo.save(e5);
     empRepo.save(e6);
     
     Dept d1=new Dept(101, "dev101", "Devlopment", Arrays.asList(e1,e2,e6));
     Dept d2=new Dept(102, "qa102", "Testing", Arrays.asList(e3,e4,e5));
     
     deptRepo.save(d1);
     deptRepo.save(d2);
		
	}

}
