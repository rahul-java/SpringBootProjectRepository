package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.EmployeeNotFoundException;
import com.app.model.Employee;
import com.app.repository.IEmpRepository;

@Service
public class EmpServiceImpl implements IEmpService {

	@Autowired
	private IEmpRepository empRepository;
	
	@Override
	public boolean saveEmp(Employee employee) {
		
		return empRepository.save(employee)!=null?true:false;
	}

	@Override
	public List<Employee> getAllEmp() {
		
		return empRepository.findAll();
	}

	@Override
	public Employee getOneEmp(Integer id) {
		
		 Employee employee = empRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee Does not EXIST!!!"));
		 return employee;
	}

	@Override
	public void deleteEmp(Integer id) {
		Employee oneEmp = getOneEmp(id);
		empRepository.delete(oneEmp);
	}

	@Override
	public void updateEmp(Employee employee) {
		empRepository.save(employee);

	}

}
