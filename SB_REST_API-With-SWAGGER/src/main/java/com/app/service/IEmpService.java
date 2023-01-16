package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface IEmpService {

	public boolean saveEmp(Employee employee);
	public List<Employee> getAllEmp();
	public Employee getOneEmp(Integer id);
	public void deleteEmp(Integer id);
	public void updateEmp(Employee employee);
}
