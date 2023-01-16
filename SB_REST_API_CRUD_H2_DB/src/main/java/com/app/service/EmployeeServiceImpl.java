package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.app.exception.EmptyInputException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Employee;
import com.app.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Override
	public Employee savEmployee(Employee employee) {
		
		if(employee.getName().isEmpty() || employee.getName().length()==0)
		{
			throw new EmptyInputException("601","Input Fields are empty...");
		}
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		/*
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent())
		{
			return optional.get();
		}
		else
		{
			throw new ResourceNotFoundException("Employee","id",id);
		}
		*/
		
		//return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		
		return employeeRepository.findById(id).orElseThrow();
	}

	@Override
	public Employee updatEmployee(Employee employee, Integer id) {
		
		//check whether emp given id exist or not.
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setName(employee.getName());
		existingEmployee.setAddress(employee.getAddress());
		return employeeRepository.save(existingEmployee);
	}

	@Override
	public void deleteEmployee(Integer id) {
		
		//check whether the employee exist in db or not.
		employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		//now delete it
		employeeRepository.deleteById(id);
		
	}

}
