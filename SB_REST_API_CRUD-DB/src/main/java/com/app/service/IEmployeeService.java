package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface IEmployeeService {

	Employee savEmployee(Employee employee);
	List<Employee> getEmployees();
	Employee getEmployeeById(Integer id);
	Employee updatEmployee(Employee employee,Integer id);
	void deleteEmployee(Integer id);
}
