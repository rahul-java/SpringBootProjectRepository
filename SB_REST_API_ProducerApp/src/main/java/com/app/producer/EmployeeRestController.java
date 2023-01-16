package com.app.producer;

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

@RestController
@RequestMapping("/emp")
public class EmployeeRestController {

	@GetMapping("/record")
	public ResponseEntity<String> viewEmp()
	{
		//return new ResponseEntity<String>("Hi Friends...", HttpStatus.OK);
		return ResponseEntity.ok("Hi Friends...");
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEmp(@RequestBody Employee emp)
	{
		return ResponseEntity.ok("data is : "+emp.getId());
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateEmp(@RequestBody Employee emp)
	{
		return ResponseEntity.ok("emp record is updated... ");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable Integer id)
	{
		return ResponseEntity.ok("emp record is deleted... "+id);
	}
	
	@GetMapping("/getOneEmp/{id}")
	public ResponseEntity<Employee> getOneEmp(@PathVariable Integer id)
	{
		return ResponseEntity.ok(new Employee(1, "Amit", "QA", "GZB", 10.5));
	}
}
