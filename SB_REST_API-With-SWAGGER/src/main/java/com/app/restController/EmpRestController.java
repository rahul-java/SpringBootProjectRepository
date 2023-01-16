package com.app.restController;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.IEmpService;

@RestController
public class EmpRestController {

	@Autowired
	private IEmpService empService;
	
	@GetMapping("/home")
	public ResponseEntity<String> showHome(){
		return new ResponseEntity<String>("Hello",HttpStatus.OK); 
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAll(){
		List<Employee> list = empService.getAllEmp();
		return new ResponseEntity<List<Employee>>(list,HttpStatus.OK); 
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEmp(@RequestBody Employee employee){
		empService.saveEmp(employee);
		return new ResponseEntity<String>("Created !",HttpStatus.OK); 
	}
	
	@GetMapping("/edit/{id}")
	public ResponseEntity<Employee> getOneEmp(@PathVariable Integer id){
		Employee oneEmp = empService.getOneEmp(id);
		return new ResponseEntity<Employee>(oneEmp,HttpStatus.OK); 
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateEmp(@RequestBody Employee employee){
		empService.updateEmp(employee);
		return new ResponseEntity<String>("Updated  !",HttpStatus.OK); 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		empService.deleteEmp(id);
		return new ResponseEntity<String>("Deleted !",HttpStatus.OK); 
	}
}
