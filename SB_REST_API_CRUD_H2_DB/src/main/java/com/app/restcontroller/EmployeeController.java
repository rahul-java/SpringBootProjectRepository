package com.app.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.repository.IEmployeeRepository;
import com.app.service.IEmployeeService;

@RestController
@RequestMapping("/rest/api/emp")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	//save employee rest api
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.savEmployee(employee),HttpStatus.CREATED);
	}
	
	/*
	//fetch all employee rest api
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEployee()
	{
		return new ResponseEntity<List<Employee>>(employeeService.getEmployees(),HttpStatus.OK);
	}*/
	
	//fetch all employee rest api
	@GetMapping("/all")
	public List<Employee> getAllEployee()
	{
		return employeeService.getEmployees();
	}
	
	//build get one employee by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getOneEmployeeById(@PathVariable("id") Integer empId)
	{
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(empId), HttpStatus.OK);
	}
	
	//build update employee by id rest api
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id")Integer id,@RequestBody Employee employee)
	{
		
		return new ResponseEntity<Employee>(employeeService.updatEmployee(employee, id), HttpStatus.OK);
		
	}
	
	//build Delete Employee Rest API
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable("id")Integer id)
	{
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee Deleted SUCCESSFULLY !!! ID==>"+id, HttpStatus.OK);
		
	}

}
