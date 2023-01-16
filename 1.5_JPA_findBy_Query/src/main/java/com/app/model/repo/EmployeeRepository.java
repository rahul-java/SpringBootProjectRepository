package com.app.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//select * from Employee where sal=10000;
	List<Employee> findBysal(double d);
	
	//select * from Employee where dept=dev;
	List<Employee> findByDept(String d);
	
	//select * from Employee where sal<5000;
	List<Employee> findBySalLessThan(double d);
	
	//select * from Employee where dept != QA;
	List<Employee> findByDeptNot(String d);
	
	//select * from Employee where sal>=5000 && dept!=QA;
	List<Employee> findBySalGreaterThanEqualAndDeptNot(double sal,String dept);
	
	//select * from Employee where name like 'S%';
	List<Employee> findByNameLike(String expression);
	
	//select * from Employee where name starting with 'R';
	List<Employee> findByNameStartingWith(String expression);
}
